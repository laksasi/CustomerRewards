CREATE TABLE IF NOT EXISTS customer (
    customer_id INT DEFAULT RANDOM_UUID() PRIMARY KEY,
    first_name VARCHAR (50) NOT NULL,
    order_amount NUMERIC NOT NULL
);