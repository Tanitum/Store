INSERT INTO client (id, name, surname)
VALUES (1, 'Petr', 'Petrov');
INSERT INTO client (id, name, surname)
VALUES (2, 'Ivan', 'Ivanov');
INSERT INTO client (id, name, surname)
VALUES (3, 'Angelina', 'Evans');
INSERT INTO client (id, name, surname)
VALUES (4, 'Anna', 'Sokolova');
INSERT INTO client (id, name, surname)
VALUES (5, 'Maria', 'Smirnova');
INSERT INTO client (id, name, surname)
VALUES (6, 'Alex', 'Allford');
INSERT INTO client (id, name, surname)
VALUES (7, 'Alexandra', 'Allford');
INSERT INTO clientorder (id, clientid, number, status, orderdate)
VALUES (1, 1, 14381, 'Сompleted', '12.04.2022');
INSERT INTO clientorder (id, clientid, number, status, orderdate)
VALUES (2, 2, 12345, 'Completed', '01.02.2022');
INSERT INTO clientorder (id, clientid, number, status, orderdate)
VALUES (3, 3, 12872, 'Сompleted', '11.04.2022');
INSERT INTO products (id, name, price, storeid)
VALUES (1, 'Butter', 140, 1);
INSERT INTO products (id, name, price, storeid)
VALUES (2, 'Tea', 500, 1);
INSERT INTO products (id, name, price, storeid)
VALUES (4, 'Bread', 120, 2);
INSERT INTO products (id, name, price, storeid)
VALUES (5, 'Milk', 115.5, 4);
INSERT INTO productsorder (id, productid, orderid, quantity)
VALUES (1, 2, 2, 10);
INSERT INTO productsorder (id, productid, orderid, quantity)
VALUES (3, 2, 3, 15);
INSERT INTO productsorder (id, productid, orderid, quantity)
VALUES (4, 1, 2, 20);
INSERT INTO productsorder (id, productid, orderid, quantity)
VALUES (5, 4, 1, 5);
INSERT INTO seller (id, storeid, sellername, password)
VALUES (1, 1, 'John-seller', '$2a$10$ATsozapL8Dl/FMz/Y0g4dec6gA3cxr0mYY9rv1qdbwwIndBsE3W4e');
INSERT INTO seller (id, storeid, sellername, password)
VALUES (2, 2, 'Petr-seller', '$2a$10$QBwbLhn9Hs7pa.H.V2RJ/uWEBPPDsnKgpvOKD36MyZRSiesOnWolq');
INSERT INTO seller (id, storeid, sellername, password)
VALUES (3, 4, 'IvanSeller', '$2a$10$iw5gieQti07diS1JUcvodePyPBZm6p5flsly2BJ8G.1CI8QFi.Uvq');
INSERT INTO seller (id, storeid, sellername, password)
VALUES (4, 1, 'XYZSeller', '$2a$10$vBmu3uGF3qSxI8vkLx.bHuft6WHwvYZpJuIh5JOt3Rj0I1/cJU2.y');
INSERT INTO seller (id, storeid, sellername, password)
VALUES (5, 8, 'MariaSeller', '$2a$10$rR2sl6RIn2ZDMxwuHSKRm.VBgzwql0GLvx3g6BL2kKbT/IuQsHOjW');
INSERT INTO seller (id, storeid, sellername, password)
VALUES (7, 8, 'AnnaSeller', '$2a$10$E8.Xt5PA3bS7oM5ueaFN7uxDsF/dXVC1p1HvPCxZjuN5EjYDcZt.W');
INSERT INTO seller (id, storeid, sellername, password)
VALUES (8, 1, 'FirstSeller', '$2a$10$een./CEAVgMPVfk/zjBzEeB5ObCcLbqI1Tbc2O33GjwBKIKCCTLRG');
INSERT INTO seller (id, storeid, sellername, password)
VALUES (9, 9, 'SecondSeller', '$2a$10$w9VTI/SRH1V0M9cIcNYJgO/IT4ynkPZvjoN39oYJEuoCZDEgg4eKm');
INSERT INTO seller (id, storeid, sellername, password)
VALUES (10, 2, 'BootmanSeller', '$2a$10$J.180HRJdsGpyZEXqeC2aOjfQAnOVapdiXPSr1LKvMfA/gMIYQlUW');
INSERT INTO store (id, name)
VALUES (1, 'Mall1');
INSERT INTO store (id, name)
VALUES (2, 'City');
INSERT INTO store (id, name)
VALUES (4, 'BestMall');
INSERT INTO store (id, name)
VALUES (7, 'TheBestStore');
INSERT INTO store (id, name)
VALUES (8, 'SuperStore');
INSERT INTO store (id, name)
VALUES (9, 'Mall1234');
INSERT INTO user (id, clientid, username, password)
VALUES (1, 1, 'U1', '$2a$10$kwA3WwqhsY3YySesBex.IOJ8arNUnBEI5zfrVPpqSxktSB2e491IC');
INSERT INTO user (id, clientid, username, password)
VALUES (2, 2, 'U2', '$2a$10$IqPbMlu9GJ5tvNlezm.mHejnxuH3j3/OvYYJUZ1z.a61r1YFDBr7u');
INSERT INTO user (id, clientid, username, password)
VALUES (3, 4, 'AnnaSokolovaUser', '$2a$10$l.cPPOQVSfYtMUjdWoiOy.TQ3FBkk/pv07/aX1RhqSb8bNvN5nTBK');
INSERT INTO user (id, clientid, username, password)
VALUES (4, 5, 'SmirnovaUser', '$2a$10$IfRssZg4HSJ.pfXAQ8zCGuEm58VJuzDzYq9JH/iG8uosG3lWSCKUS');
INSERT INTO user (id, clientid, username, password)
VALUES (5, 6, 'XXX', '$2a$10$xn8MGPykiIF0ZwdNUBvJSOf3Aay6xx.wodFPrxD9j8veW11h/6V7W');
INSERT INTO user (id, clientid, username, password)
VALUES (6, 7, 'YYY', '$2a$10$blgJv8VVp/vM3jcMdcx8C.XAtYF.qIUh0kre8F7jQFCSezBhUCFni');
INSERT INTO user (id, clientid, username, password)
VALUES (7, 3, 'Evangel', '$2a$10$YS12S5kl/3CEFsbhSRyvj.TusuVL9ipsS74kgc2/4238cbxNG67.W');