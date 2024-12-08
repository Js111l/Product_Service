
CREATE SEQUENCE IF NOT EXISTS main_page_banner_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS MAIN_PAGE_BANNER (
    id bigint PRIMARY KEY DEFAULT nextval('main_page_banner_sequence'::regclass),
    image_url VARCHAR(255) NOT NULL,
    active boolean
);
