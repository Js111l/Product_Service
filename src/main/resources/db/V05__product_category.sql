CREATE SEQUENCE IF NOT EXISTS product_category_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;


CREATE TABLE PRODUCT_CATEGORY (
    id bigint PRIMARY KEY DEFAULT nextval('product_category_sequence'::regclass),
    path VARCHAR(255) NOT NULL,
    label VARCHAR(255) NOT NULL,
    parent_category_id bigint,
    CONSTRAINT parent_category_id_fkey FOREIGN KEY (parent_category_id) REFERENCES product_category (id)
);

CREATE SEQUENCE IF NOT EXISTS product_category_map_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;


CREATE TABLE PRODUCT_CATEGORY_MAP(
    id bigint PRIMARY KEY DEFAULT nextval('product_category_map_sequence'::regclass),
    category_id bigint,
    product_id bigint,
   CONSTRAINT category_id_fkey FOREIGN KEY (category_id) REFERENCES product_category (id),
   CONSTRAINT product_id_fkey FOREIGN KEY (product_id) REFERENCES product (id)
);