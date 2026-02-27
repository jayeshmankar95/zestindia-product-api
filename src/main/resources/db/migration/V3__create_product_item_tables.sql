CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,
    created_by VARCHAR(100),
    created_on DATETIME,
    modified_by VARCHAR(100),
    modified_on DATETIME
);

CREATE TABLE item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quantity INT NOT NULL,
    product_id INT,
    
    CONSTRAINT fk_item_product
        FOREIGN KEY (product_id)
        REFERENCES product(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_item_product_id ON item(product_id);

-- ===============================
-- INSERT SAMPLE PRODUCTS
-- ===============================

INSERT INTO product (product_name, created_by, created_on, modified_by, modified_on)
VALUES
('Laptop', 'admin', NOW(), 'admin', NOW()),
('Mobile Phone', 'admin', NOW(), 'admin', NOW()),
('Tablet', 'admin', NOW(), 'admin', NOW()),
('Smart Watch', 'admin', NOW(), 'admin', NOW()),
('Headphones', 'admin', NOW(), 'admin', NOW());

-- ===============================
-- INSERT SAMPLE ITEMS
-- ===============================

INSERT INTO item (quantity, product_id)
VALUES
(10, 1),
(25, 2),
(15, 3),
(30, 4),
(50, 5);