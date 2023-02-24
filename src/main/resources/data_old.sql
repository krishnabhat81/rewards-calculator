-- Customers
INSERT INTO CUSTOMER (id, name) VALUES (1, 'John');

-- Transactions for February
INSERT INTO TRANSACTION (id, customer_id, amount, date) VALUES (1, 1, 100.00, '2022-02-01');
INSERT INTO TRANSACTION (id, customer_id, amount, date) VALUES (2, 1, 200.00, '2022-02-10');
INSERT INTO TRANSACTION (id, customer_id, amount, date) VALUES (3, 1, 300.00, '2022-02-20');

-- Transactions for March
INSERT INTO TRANSACTION (id, customer_id, amount, date) VALUES (4, 1, 150.00, '2022-03-05');
INSERT INTO TRANSACTION (id, customer_id, amount, date) VALUES (5, 1, 250.00, '2022-03-10');
INSERT INTO TRANSACTION (id, customer_id, amount, date) VALUES (6, 1, 350.00, '2022-03-15');
INSERT INTO TRANSACTION (id, customer_id, amount, date) VALUES (7, 1, 450.00, '2022-03-25');

-- Transactions for April
INSERT INTO TRANSACTION (id, customer_id, amount, date) VALUES (8, 1, 1000.00, '2022-04-01');
INSERT INTO TRANSACTION (id, customer_id, amount, date) VALUES (9, 1, 2000.00, '2022-04-10');
INSERT INTO TRANSACTION (id, customer_id, amount, date) VALUES (10, 1, 3000.00, '2022-04-20');

-- Customer transactions for February
INSERT INTO CUSTOMER_TRANSACTIONS (customer_id, transactions_id) VALUES (1, 1);
INSERT INTO CUSTOMER_TRANSACTIONS (customer_id, transactions_id) VALUES (1, 2);
INSERT INTO CUSTOMER_TRANSACTIONS (customer_id, transactions_id) VALUES (1, 3);

-- Customer transactions for March
INSERT INTO CUSTOMER_TRANSACTIONS (customer_id, transactions_id) VALUES (1, 4);
INSERT INTO CUSTOMER_TRANSACTIONS (customer_id, transactions_id) VALUES (1, 5);
INSERT INTO CUSTOMER_TRANSACTIONS (customer_id, transactions_id) VALUES (1, 6);
INSERT INTO CUSTOMER_TRANSACTIONS (customer_id, transactions_id) VALUES (1, 7);

-- Customer transactions for April
INSERT INTO CUSTOMER_TRANSACTIONS (customer_id, transactions_id) VALUES (1, 8);
INSERT INTO CUSTOMER_TRANSACTIONS (customer_id, transactions_id) VALUES (1, 9);
INSERT INTO CUSTOMER_TRANSACTIONS (customer_id, transactions_id) VALUES (1, 10);

