CREATE TABLE IF NOT EXISTS customers (
    id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(30) NOT NULL,
    
    PRIMARY KEY (id)
);
