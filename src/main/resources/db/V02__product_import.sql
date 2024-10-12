CREATE SEQUENCE IF NOT EXISTS product_import_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;


CREATE TABLE PRODUCT_IMPORT(
    id bigint PRIMARY KEY DEFAULT nextval('product_import_sequence'::regclass),
    created_date timestamp without time zone	NOT NULL,
    total_imported INTEGER,
    total_not_imported INTEGER,
    user_name VARCHAR(255),
    uuid VARCHAR(255)
);