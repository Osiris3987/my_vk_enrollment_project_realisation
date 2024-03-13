package com.example.vk_enrollment_project.web.security.expression;

import com.example.vk_enrollment_project.domain.entity.security_user.Role;
import com.example.vk_enrollment_project.service.AccountService;
import com.example.vk_enrollment_project.web.security.JwtEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Setter
@Getter
public class CustomMethodSecurityExpressionRoot
        extends SecurityExpressionRoot
        implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;
    private HttpServletRequest request;

    private AccountService userService;

    public CustomMethodSecurityExpressionRoot(
            final Authentication authentication
    ) {
        super(authentication);
    }

    public boolean canAccessUser(final Long id) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();

        return userId.equals(id) || hasAnyRole(authentication, Role.ROLE_ADMIN);
    }

    private boolean hasAnyRole(final Authentication authentication,
                               final Role... roles) {
        for (Role role : roles) {
            SimpleGrantedAuthority authority
                    = new SimpleGrantedAuthority(role.name());
            if (authentication.getAuthorities().contains(authority)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasUsersAccessRole() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return hasAnyRole(authentication, Role.ROLE_USERS, Role.ROLE_ADMIN);
    }

    public boolean hasPostsAccessRole() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return hasAnyRole(authentication, Role.ROLE_POSTS, Role.ROLE_ADMIN);
    }

    public boolean hasAlbumsAccessRole() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return hasAnyRole(authentication, Role.ROLE_ALBUMS, Role.ROLE_ADMIN);
    }

    @Override
    public Object getThis() {
        return target;
    }

}
