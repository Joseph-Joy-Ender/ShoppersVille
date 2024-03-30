truncate table product;
# truncate table item;
# truncate table customer;

insert into product(id, name, description, quantity) values
(200, 'milk-shake', 'a beverage', 1),
(201, 'eggs', 'enjoyable eggs', 2);

# insert into item(id, name, price, quantity) values
# (200, 'milk-shake', 20.00, 5),
# # (201, 'eggs', 50.00, 2);
#
# insert into customer(id, email, password) values
# (100, 'testing@gmail.com', 'password'),
# (101, 'akpan@gmail.com', 'password112');
