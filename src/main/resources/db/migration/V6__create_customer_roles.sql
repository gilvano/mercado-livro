CREATE TABLE customer_roles(
    customer_id INTEGER NOT NULL,
    role varchar(50) NOT NULL,
    foreign key (customer_id) references customer(id)
);