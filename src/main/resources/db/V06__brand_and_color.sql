CREATE SEQUENCE IF NOT EXISTS color_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE TABLE COLOR (
    id bigint PRIMARY KEY DEFAULT nextval('color_sequence'::regclass),
    name VARCHAR(255) NOT NULL,
    hex_code VARCHAR(7),  -- Optional: Store hex code (e.g., #FF5733)
    CONSTRAINT color_name_unique UNIQUE (name)
);


-- Create sequence for Brand table
CREATE SEQUENCE IF NOT EXISTS brand_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

-- Create Brand table
CREATE TABLE BRAND (
    id bigint PRIMARY KEY DEFAULT nextval('brand_sequence'::regclass),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    CONSTRAINT brand_name_unique UNIQUE (name)
);



-- Add new columns to the existing PRODUCT table
ALTER TABLE PRODUCT
    ADD COLUMN color_id BIGINT,  -- Foreign key for color, referencing the Color table
    ADD COLUMN brand_id BIGINT,  -- Foreign key for brand, referencing the Brand table
    ADD COLUMN fabric VARCHAR(255),  -- Enum column for fabric (store fabric types as strings)
    ADD COLUMN sleeve_length VARCHAR(50),  -- Enum column for sleeve length (store sleeve lengths as strings)
    ADD COLUMN style VARCHAR(255);  -- Enum column for style (store styles as strings)

-- Add foreign key constraints for color_id and brand_id
ALTER TABLE PRODUCT
    ADD CONSTRAINT fk_color FOREIGN KEY (color_id) REFERENCES COLOR(id),
    ADD CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES BRAND(id);
