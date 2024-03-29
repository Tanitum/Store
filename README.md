# Store

### Описание Проекта:
Проект Store на Java, весна 2022.
В проекте реализовано приложение, которое облегчает заказ товаров из магазина с интернет доставкой.
Клиенты могут наполнить онлайн корзину товарами одного из интернет магазинов. Продавцы могут увидеть все заказы в
своем магазине, могут изменить статусы заказов своих клиентов. Модули (или объекты): покупатели (Client),
магазины (Store), товары (Products), заказы (Order), корзины (ProductsOrder), данные авторизации (User), продавцы (
Seller).

Проект написан на Java, использован Maven для автоматизации сборки проекта, Hibernate для работы с базой данных,
реализовано 3 уровня доступа, шифрование паролей, фоновый процесс инфляция, подключен Swagger, есть генерация Javadoc.

Во время работы программы активирован фоновый процесс Инфляция, который будет немного увеличивать цены на все продукты
каждые 10 секунд.

По похожей тематике написан [проект по базам данных](https://github.com/Tanitum/Database_project_2023).

### Описание запуска приложения:
1.1. Запустить класс main

или

1.2. ЭТОТ СПОСОБ СЕЙЧАС НЕ РАБОТАЕТ С ДАННЫМИ БД. Войти в режим командной строки (команда cmd), перейти в папку с
pom.xml и ввести команду mvn clean package.
Maven cгенерирует исполняемый jar-файл с именем Store-1.0.jar.
Перейдите в папку cd target.
Затем запустите jar-файл: java -jar Store-1.0.jar.

2. Запустить одну из доступных команд в браузере.

3. Для окончания использования localhost:8089 нужно остановить класс main в первом способе, остановить процесс Java(TM)
   Platform SE binary во втором способе.

### Генерация Javadoc (2 способа):

1) Запустить команду mvn javadoc:javadoc, в папке target/site/apidocs запустить index.html.
2) Запустить команду mvn clean package, генерируется весь проект и jar файлы, в том числе Store-1.0-javadoc.jar, в папке
   target/apidocs запустить index.html.

### Информация о доступных командах:
Узнать информацию обо всех доступных командах: http://localhost:8089/

http://localhost:8089/... - команды, которые доступны всем без авторизации

http://localhost:8089/user/... - команды, которые доступны авторизованным пользователям и админу

http://localhost:8089/seller/... - команды, которые доступны авторизованным продавцам и админу

http://localhost:8089/admin/... - команды, которые доступны только админу

Авторизация возможна несколько раз после старта программы.
Если нужно переавторизоваться, то необходимо ввести http://localhost:8089/logout или перезапустить программу.

Для сохранения, изменения и удаления данных нужно использовать Swagger, перед его использованием необходимо
авторизоваться. http://localhost:8089/swagger-ui.html - команда для использования Swagger.

### Информация об уровнях доступа:
Есть 1 админ, 1 пользователь, 1 продавец для проверки работоспособности проекта:

1) логин админа:admin пароль админа:admin
2) логин пользователя:user пароль пользователя:user
3) логин продавца:seller пароль продавца:seller

Также авторизоваться могут все пользователи, username и password которых есть в таблице user базы данных. Например,
username= U1 , password= P1

Также авторизоваться могут все продавцы, sellername и password которых есть в таблице seller базы данных. Например,
sellername= Petr-seller , password= 123

### Информация о модулях:

1) Client (client в базе данных): id, name, surname
2) ProductsOrder (productsorder в базе данных): id, productid, orderid, quantity
3) Order (clientorder в базе данных): id, number, status, orderdate
4) Products (products в базе данных): id, storeid, name, price
5) Store (store в базе данных): id, name
6) User (user в базе данных): id, clientid, username, password
7) Seller (seller в базе данных): id, storeid, sellername, password
