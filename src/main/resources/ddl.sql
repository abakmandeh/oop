CREATE TABLE item (
                       id SERIAL PRIMARY KEY,
                       item_name VARCHAR(255) NOT NULL,
                       item_content TEXT,
                       item_type INTEGER
);
