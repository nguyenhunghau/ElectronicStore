-- src/main/resources/data.sql

-- Insert into account table
INSERT INTO account (username, password) VALUES ('admin', '$2a$12$5518HkKZWlEHp.77iMeLjeMH82o8jIzDyowOLXdk0gpjj7kA5O8Ou');
INSERT INTO account (username, password) VALUES ('customer', '$2a$12$5518HkKZWlEHp.77iMeLjeMH82o8jIzDyowOLXdk0gpjj7kA5O8Ou');

-- Insert into account_roles table
INSERT INTO account_roles (account_id, roles) VALUES (1, 'ROLE_ADMIN');
INSERT INTO account_roles (account_id, roles) VALUES (2, 'ROLE_CUSTOMER');

-- Insert into product table
INSERT INTO product (name, description, price) VALUES ('Product 1', 'Description for Product 1', 50.0);
INSERT INTO product (name, description, price) VALUES ('Product 2', 'Description for Product 2', 75.0);

-- Insert into discount_deal table
INSERT INTO discount_deal (description, discount_percentage, required_quantity, required_price, discount_type)
VALUES ('10% off on orders above $100', 10.0, NULL, 100.0, 'PRICE');

INSERT INTO discount_deal (description, discount_percentage, required_quantity, required_price, discount_type)
VALUES ('Discount 50% from 2nd item', 50.0, 2, 0.0, 'QUANTITY');