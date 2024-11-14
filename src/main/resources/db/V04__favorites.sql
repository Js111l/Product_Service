CREATE SEQUENCE IF NOT EXISTS user_product_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE TABLE USER_PRODUCT(
    id bigint PRIMARY KEY DEFAULT nextval('user_product_sequence'::regclass),
    product_id bigint,
    user_session_id text,
    CONSTRAINT favorite_product_product_id_fkey FOREIGN KEY (product_id) REFERENCES product (id)
);