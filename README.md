# Store
Store project, весна 2022.
В проекте будет реализовано приложение, которое облегчает заказ товаров из магазина с интернет доставкой. 
Клиенты смогут наполнить онлайн корзину товарами одного из интернет магазинов. Продавцы смогут увидеть все заказы в своем магазине,
смогут изменить статусы заказов своих клиентов. Модули (или объекты): покупатели (Client), 
магазины (Store), товары (Products), заказы (Order), корзины (ProductsOrder), данные авторизации (User), продавцы (Seller).
В перспективе возможно добавление чата между продавцом и клиентом.

Описание запуска приложения:
1.1) Запустить класс main 
или
1.2) ЭТОТ СПОСОБ СЕЙЧАС НЕ РАБОТАЕТ С ДАННЫМИ БД. Войти в режим командной строки (команда cmd), перейти в папку с pom.xml и ввести команду mvn clean package
Maven cгенерирует исполняемый jar-файл с именем Store-1.0.jar
Перейдите в папку cd target
Затем запустите jar-файл: java -jar Store-1.0.jar
2) Запустить одну из доступных команд в браузере.
3) Для окончания использования localhost:8089 нужно остановить класс main в первом способе, остановить процесс Java(TM) Platform SE binary во втором способе.

Узнать информацию обо всех доступных командах: http://localhost:8089/
http://localhost:8089/... - команды, которые доступны всем без авторизации
http://localhost:8089/user/... - команды, которые доступны авторизованным пользователям и админу
http://localhost:8089/seller/... - команды, которые доступны авторизованным продавцам и админу
http://localhost:8089/admin/... - команды, которые доступны только админу

Авторизация возможна несколько раз после старта программы.
Если нужно переавторизоваться, то необходимо ввести http://localhost:8089/logout или перезапустить программу.

Есть 1 админ, 1 пользователь, 1 продавец для проверки работоспособности проекта:
логин админа:admin
пароль админа:admin
логин пользователя:user
пароль пользователя:user
логин продавца:seller
пароль продавца:seller
Также авторизоваться могут все пользователи, username и password которых есть в таблице user базы данных.
Например, username= U1 , password= P1
Также авторизоваться могут все продавцы, sellername и password которых есть в таблице seller базы данных.
Например, sellername= Petr-seller , password= 123

Информация о модулях:
Client (client в базе данных): id, name, surname
ProductsOrder (productsorder в базе данных): id, productid, orderid, quantity
Order (clientorder в базе данных): id, number, status, orderdate
Products (products в базе данных): id, storeid, name, price
Store (store в базе данных): id, name
User (user в базе данных): id, clientid, username, password
Seller (seller в базе данных): id, storeid, sellername, password
