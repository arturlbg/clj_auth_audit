CREATE TABLE audit (
  id SERIAL PRIMARY KEY,
  registered_user_id INTEGER REFERENCES registered_user(id),
  http_method VARCHAR(10) NOT NULL,
  endpoint VARCHAR(255) NOT NULL,
  request TEXT,
  response TEXT,
  date_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- classify products
CREATE TABLE categories (
                            category_id SERIAL PRIMARY KEY,
                            category_name VARCHAR(255) NOT NULL
);

-- for storing basic product information
CREATE TABLE products (
                          product_id SERIAL PRIMARY KEY,
                          product_name VARCHAR(255) NOT NULL,
                          description TEXT,
                          category_id INT,
                          price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE SET NULL
);

-- track stock levels for products
CREATE TABLE inventory (
                           inventory_id SERIAL PRIMARY KEY,
                           product_id INT NOT NULL,
                           quantity INT NOT NULL CHECK (quantity >= 0),
                           last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE
);

-- for storing supplier information
CREATE TABLE suppliers (
                           supplier_id SERIAL PRIMARY KEY,
                           supplier_name VARCHAR(255) NOT NULL,
                           contact_name VARCHAR(255),
                           contact_email VARCHAR(255),
                           contact_phone VARCHAR(20)
);

-- store relationships between products and suppliers
CREATE TABLE product_suppliers (
                                   product_id INT NOT NULL,
                                   supplier_id INT NOT NULL,
                                   PRIMARY KEY (product_id, supplier_id),
                                   FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE,
                                   FOREIGN KEY (supplier_id) REFERENCES suppliers (supplier_id) ON DELETE CASCADE
);

-- for storing customer orders
CREATE TABLE orders (
                        order_id SERIAL PRIMARY KEY,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        total_amount DECIMAL(10, 2) NOT NULL CHECK (total_amount >= 0)
);

-- for storing product items in an order
CREATE TABLE order_items (
                             order_item_id SERIAL PRIMARY KEY,
                             order_id INT NOT NULL,
                             product_id INT NOT NULL,
                             quantity INT NOT NULL CHECK (quantity > 0),
                             price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
                             FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
                             FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE
);

-- Create a view to summarize product information
CREATE VIEW product_summary AS
SELECT
    p.product_id,
    p.product_name,
    c.category_name,
    p.price,
    i.quantity AS stock
FROM
    products p
        LEFT JOIN
    categories c ON p.category_id = c.category_id
        LEFT JOIN
    inventory i ON p.product_id = i.product_id;




