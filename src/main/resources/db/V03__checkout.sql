CREATE SEQUENCE IF NOT EXISTS checkout_product_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;


CREATE TABLE CHECKOUT_PRODUCT(
    id bigint PRIMARY KEY DEFAULT nextval('checkout_product_sequence'::regclass),
    product_id bigint,
    session_id text,
    quantity integer,
    CONSTRAINT checkout_product_product_id_fkey FOREIGN KEY (product_id) REFERENCES product (id)
);