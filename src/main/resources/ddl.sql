CREATE TABLE ecart.order (
  id bigint NOT NULL,
  status varchar(50) NOT NULL,
  total_amount float(11,3) NOT NULL,
  customer_id bigint NOT NULL,
  shipping_address varchar(255) NOT NULL,
  billing_address varchar(255) NOT NULL,
  create_date timestamp NULL DEFAULT NULL,
  update_date timestamp DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE ecart.product_stock (
  product_id bigint NOT NULL,
  available int NOT NULL default 0,
  reserved int NOT NULL default 0,
  create_date timestamp,
  update_date timestamp,
  PRIMARY KEY (product_id)
);

CREATE TABLE ecart.order_item (
  id bigint NOT NULL,
  order_id bigint NOT NULL,
  product_id bigint NOT NULL,
  quantity int NOT NULL,
  create_date timestamp,
  update_date timestamp,
  PRIMARY KEY (id)
);

CREATE TABLE ecart.payment_transaction (
  id bigint NOT NULL,
  order_id bigint NOT NULL,
  payment_status varchar(255) NOT NULL,
  payment_instrument varchar(255) NOT NULL,
  payment_gateway_id varchar(255) NOT NULL,	
  create_date timestamp,
  update_date timestamp,
  PRIMARY KEY (id)
);