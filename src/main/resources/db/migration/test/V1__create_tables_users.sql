CREATE SCHEMA if not exists security AUTHORIZATION user_nisum;
SET SCHEMA security;

CREATE TABLE security.users (
    user_id uuid NOT NULL,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    password varchar(255) NOT NULL,
    created timestamp NULL,
    modified timestamp NULL,
    last_login timestamp NULL,
    token varchar(1000) NULL,
    is_active bool NOT NULL DEFAULT true,
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);
CREATE INDEX index_users_email ON security.users USING btree (email);


CREATE TABLE security.user_phones (
    user_phones_id uuid NOT NULL,
    phone_number varchar(20) NOT NULL,
    city_code varchar(10) NOT NULL,
    country_code varchar(10) NOT NULL,
    user_id uuid NOT NULL,
    created timestamp NULL,
    modified timestamp NULL,
    CONSTRAINT tbl_user_phones_pkey PRIMARY KEY (user_phones_id),
    CONSTRAINT uni_tbl_user_phones_number_city_code_user_id UNIQUE (phone_number, city_code, country_code, user_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES security.users(user_id) ON DELETE CASCADE
);