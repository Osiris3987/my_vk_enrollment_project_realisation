package com.example.vk_enrollment_project.config.feign_client_config;

import feign.Feign;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExceptionHandlingFeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder.Default defaultDecoder = new Default();
    private final ApplicationContext applicationContext;

    private final Map<String, FeignExceptionHandler> exceptionHandlerMap = new HashMap<>();

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> feignClients = applicationContext.getBeansWithAnnotation(FeignClient.class);
        List<Method> clientMethods = feignClients.values().stream()
                .map(Object::getClass)
                .map(aClass -> aClass.getInterfaces()[0])
                .map(ReflectionUtils::getDeclaredMethods)
                .flatMap(Arrays::stream)
                .toList();
        for (Method m : clientMethods) {
            String configKey = Feign.configKey(m.getDeclaringClass(), m);
            HandleFeignError handlerAnnotation = getHandleFeignErrorAnnotation(m);
            if (handlerAnnotation != null) {
                FeignExceptionHandler handler = applicationContext.getBean(handlerAnnotation.value());
                exceptionHandlerMap.put(configKey, handler);
            }
        }
    }

    private HandleFeignError getHandleFeignErrorAnnotation(Method m) {
        HandleFeignError result = m.getAnnotation(HandleFeignError.class);
        if (result == null) {
            result = m.getDeclaringClass().getAnnotation(HandleFeignError.class);
        }
        return result;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignExceptionHandler handler = exceptionHandlerMap.get(methodKey);
        if (handler != null) {
            return handler.handle(response);
        }
        return defaultDecoder.decode(methodKey, response);
    }
}
