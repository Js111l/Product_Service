CREATE SEQUENCE IF NOT EXISTS product_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS PRODUCT (
    id bigint PRIMARY KEY DEFAULT nextval('product_sequence'::regclass),
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    created_date timestamp without time zone	NOT NULL,
    start_date DATE,
    end_date DATE,
    image_url VARCHAR(255),
    detail_url VARCHAR(255)
);
