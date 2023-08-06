CREATE TABLE IF NOT EXISTS size
(
	id BIGINT NOT NULL PRIMARY KEY,
	product_id BIGINT NOT NULL,
	back_soon BOOLEAN NOT NULL,
	special BOOLEAN NOT NULL,
	quantity INTEGER 
);

ALTER TABLE size ADD FOREIGN KEY (product_id) REFERENCES product(id)