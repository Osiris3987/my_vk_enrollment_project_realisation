CREATE TABLE audit_log (
    id bigserial PRIMARY KEY,
    date_time timestamp,
    username varchar(255),
    request_params jsonb,
    request_type varchar(32),
    domain varchar(255)
);