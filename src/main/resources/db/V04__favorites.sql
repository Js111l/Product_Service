CREATE SEQUENCE IF NOT EXISTS user_product_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE TABLE USER_PRODUCT(
    id bigint PRIMARY KEY DEFAULT nextval('user_product_sequence'::regclass),
    product_id bigint,
    user_id bigint,
    CONSTRAINT favorite_product_product_id_fkey FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE SEQUENCE IF NOT EXISTS user_product_mapping_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE TABLE USER_PRODUCT_MAPPING(
    id bigint PRIMARY KEY DEFAULT nextval('user_product_mapping_sequence'::regclass),
    product_id bigint,
    user_product_id bigint,
    CONSTRAINT user_product_mapping_product_id_fkey FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT user_product_mapping_user_fav_id_fkey FOREIGN KEY (user_product_id) REFERENCES user_product(id)
)