package myapp.Controllers;

import dao.*;
import model.*;
import myapp.Services.CurrentDate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class Controller {

    @GetMapping("/store")
    public String Allstores() {
        StoreDao storeDao = new StoreDao();
        return storeDao.findAll().toString();
    }

    @GetMapping("/getdate")
    public String GetDate() throws Exception {
        return "Текущая дата: " + CurrentDate.Get_current_date();
    }

    @PatchMapping("/admin/setdate/{date}")
    public String SetDate(@PathVariable("date") String date) throws Exception {
        Date Date = CurrentDate.formater.parse(date);
        CurrentDate.Set_current_date(CurrentDate.formater.format(Date));
        return "Установлена новая дата. " + GetDate();
    }

    @GetMapping("/admin/client")
    public String Allclients() {
        ClientDao clientDao = new ClientDao();
        return clientDao.findAll().toString();
    }

    @GetMapping("/admin/users")
    public String Allusers() {
        UserDao userDao = new UserDao();
        return userDao.findAll().toString();
    }

    @GetMapping("/admin/sellers")
    public String Allsellers() {
        SellerDao sellerDao = new SellerDao();
        return sellerDao.findAll().toString();
    }

    @GetMapping("/seller/info")
    public String GetSellerInfo() {
        SellerDao sellerDao = new SellerDao();
        return sellerDao.findSellerBySellername(GetLoginname()).toString();
    }

    @GetMapping("/seller/products")
    public List<String> GetProductsOfSellerStore() {
        SellerDao sellerDao = new SellerDao();
        int storeid = sellerDao.findSellerBySellername(GetLoginname()).GetStoreId();
        ProductsDao productsDao = new ProductsDao();
        List<Products> Products = productsDao.findProductsByStoreId(storeid);
        List<String> ProductsStr = new ArrayList<>();
        for (int i = 0; i < Products.size(); i++) {
            ProductsStr.add(Products.get(i).GetProductInfoForSeller());
        }
        return ProductsStr;
    }

    @GetMapping("/seller/orders")
    public List<Integer> GetAllOrdersOfSellerStore() {
        SellerDao sellerDao = new SellerDao();
        OrderDao orderDao = new OrderDao();
        int storeid = sellerDao.findSellerBySellername(GetLoginname()).GetStoreId();
        ProductsDao productsDao = new ProductsDao();
        List<Products> Products = productsDao.findProductsByStoreId(storeid);
        ProductsOrderDao productsOrderDao = new ProductsOrderDao();
        List<ProductsOrder> ProductsOrder = productsOrderDao.findAll();
        List<Integer> Orders = new ArrayList<>();
        for (int i = 0; i < ProductsOrder.size(); i++) {
            for (int j = 0; j < Products.size(); j++) {
                if (ProductsOrder.get(i).GetProductId() == Products.get(j).GetId()) {
                    int k = 0;
                    Order order = orderDao.findOrderById(ProductsOrder.get(i).GetOrderId());
                    for (int l = 0; l < Orders.size(); l++) {
                        if (Orders.get(l) == order.GetNumber()) {
                            k = 1;
                        }
                    }
                    if (k == 0) {
                        Orders.add(order.GetNumber());
                    }
                }
            }
        }
        return Orders;
    }

    @GetMapping("/seller/order/{order_number}")
    public String OrderInfoByNumber(@PathVariable("order_number") Integer order_number) {
        OrderDao orderDao = new OrderDao();
        List<Integer> orders = GetAllOrdersOfSellerStore();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).equals(order_number)) {
                return orderDao.findOrderByNumber(order_number).GetOrderInfoForSeller();
            }
        }
        return "Это заказ не из магазина авторизированного продавца, или заказа нет в базе данных.";
    }

    @PatchMapping("/seller/orderstatus/{order_number}/{order_status}")
    public String ChangeOrderStatus(@PathVariable("order_number") Integer order_number, @PathVariable("order_status") String order_status) {
        OrderDao orderDao = new OrderDao();
        List<Integer> orders = GetAllOrdersOfSellerStore();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).equals(order_number)) {
                Order order = orderDao.findOrderByNumber(order_number);
                Order neworder = new Order(order.GetId(), order.GetClientId(), order_number, order_status, order.GetOrderdate());
                orderDao.update(neworder);
                return "Статус заказа номер " + order_number + " был обновлен. Раньше был статус в базе данных: " + order.GetStatus() + ". Теперь он стал: " + neworder.GetStatus();
            }
        }
        return "Это заказ не из магазина авторизированного продавца, или заказа нет в базе данных. Статус не изменён.";
    }

    @GetMapping("/seller/clientbyorder/{order_number}")
    public String ClientInfoByOrderNumber(@PathVariable("order_number") Integer order_number) {
        OrderDao orderDao = new OrderDao();
        List<Integer> orders = GetAllOrdersOfSellerStore();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).equals(order_number)) {
                ClientDao clientDao = new ClientDao();
                return clientDao.findClientById(orderDao.findOrderByNumber(order_number).GetClientId()).GetClientInfoForSeller();
            }
        }
        return "Это заказ не из магазина авторизированного продавца, или заказа нет в базе данных. Невозможно узнать информацию о клиенте.";
    }

    @GetMapping("/seller/orderproducts/{order_number}")
    public List<String> OrderProductsByNumber(@PathVariable("order_number") Integer order_number) {
        OrderDao orderDao = new OrderDao();
        List<Integer> orders = GetAllOrdersOfSellerStore();
        List<String> OrderProducts = new ArrayList<>();
        Double totalcost = 0.0;
        ProductsOrderDao productsOrderDao = new ProductsOrderDao();
        List<ProductsOrder> ProductsOrder = productsOrderDao.findAll();
        ProductsDao productsDao = new ProductsDao();
        SellerDao sellerDao = new SellerDao();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).equals(order_number)) {
                int orderid = orderDao.findOrderByNumber(order_number).GetId();
                for (int j = 0; j < ProductsOrder.size(); j++) {
                    if (ProductsOrder.get(j).GetOrderId() == orderid) {
                        Products products = productsDao.findProductsById(ProductsOrder.get(j).GetProductId());
                        if (products.GetStoreId() == sellerDao.findSellerBySellername(GetLoginname()).GetStoreId()) {
                            totalcost += products.GetPrice() * ProductsOrder.get(j).GetQuantity();
                            OrderProducts.add(products.GetName() + ";" + products.GetPrice() + ";" + ProductsOrder.get(j).GetQuantity() + ";" + products.GetPrice() * ProductsOrder.get(j).GetQuantity());
                        }
                    }
                }
                OrderProducts.add("Итоговая стоимость заказа: " + totalcost.toString());
                return OrderProducts;
            }
        }
        OrderProducts.add("Это заказ не из магазина авторизированного продавца, или заказа нет в базе данных.");
        return OrderProducts;
    }

    @GetMapping("/user/info")
    public String GetUserInfo() {
        UserDao userDao = new UserDao();
        return userDao.findUserByusername(GetLoginname()).toString();
    }

    @GetMapping("/user/products")
    public List<String> Allproducts() {
        ProductsDao productsDao = new ProductsDao();
        List<Products> Products = productsDao.findAll();
        List<String> ProductsStr = new ArrayList<>();
        StoreDao storeDao = new StoreDao();
        for (int i = 0; i < Products.size(); i++) {
            ProductsStr.add(storeDao.findStoreById(Products.get(i).GetStoreId()).GetName() + ";" + Products.get(i).GetName() + ";" + Products.get(i).GetPrice());
        }
        return ProductsStr;
    }

    @GetMapping("/user/products/{store_name}")
    public List<String> AllproductsOfStore(@PathVariable("store_name") String store_name) {
        StoreDao storeDao = new StoreDao();
        int storeid = storeDao.findStoreByName(store_name).GetId();
        ProductsDao productsDao = new ProductsDao();
        List<Products> Products = productsDao.findProductsByStoreId(storeid);
        List<String> ProductsStr = new ArrayList<>();
        for (int i = 0; i < Products.size(); i++) {
            ProductsStr.add(store_name + ";" + Products.get(i).GetName() + ";" + Products.get(i).GetPrice());
        }
        return ProductsStr;
    }

    @GetMapping("/user/orders")
    public List<Integer> GetAllOrdersOfClient() {
        UserDao userDao = new UserDao();
        OrderDao orderDao = new OrderDao();
        int clientid = userDao.findUserByusername(GetLoginname()).GetUserClientId();
        List<Integer> Orders = new ArrayList<>();
        for (int i = 0; i < orderDao.findAll().size(); i++) {
            if (orderDao.findAll().get(i).GetClientId() == clientid) {
                Orders.add(orderDao.findAll().get(i).GetNumber());
            }
        }
        return Orders;
    }

    @GetMapping("/user/orderproducts/{order_number}")
    public List<String> ProductsByOrderNumber(@PathVariable("order_number") Integer order_number) {
        OrderDao orderDao = new OrderDao();
        List<Integer> orders = GetAllOrdersOfClient();
        List<String> OrderProducts = new ArrayList<>();
        Double totalcost = 0.0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).equals(order_number)) {
                int orderid = orderDao.findOrderByNumber(order_number).GetId();
                ProductsOrderDao productsOrderDao = new ProductsOrderDao();
                List<ProductsOrder> ProductsOrder = productsOrderDao.findAll();
                ProductsDao productsDao = new ProductsDao();
                for (int j = 0; j < ProductsOrder.size(); j++) {
                    if (ProductsOrder.get(j).GetOrderId() == orderid) {
                        Products products = productsDao.findProductsById(ProductsOrder.get(j).GetProductId());
                        totalcost += products.GetPrice() * ProductsOrder.get(j).GetQuantity();
                        OrderProducts.add(products.GetName() + ";" + products.GetPrice() + ";" + ProductsOrder.get(j).GetQuantity() + ";" + products.GetPrice() * ProductsOrder.get(j).GetQuantity());
                    }
                }
                OrderProducts.add("Итоговая стоимость заказа: " + totalcost.toString());
                return OrderProducts;
            }
        }
        OrderProducts.add("Это заказ неавторизированного пользователя, или заказа нет в базе данных.");
        return OrderProducts;
    }

    @PutMapping("/user/order/save/{order_number}")
    public String CreateNewOrder(@PathVariable("order_number") Integer order_number) throws Exception {
        OrderDao orderDao = new OrderDao();
        UserDao userDao = new UserDao();
        int clientid = userDao.findUserByusername(GetLoginname()).GetUserClientId();
        Order order = new Order(clientid, order_number, "Created", CurrentDate.Get_current_date());
        orderDao.save(order);
        return "Исходно пустой заказ создан. Вот информация о нём: " + order.GetOrderInfoForClient();
    }

    @PutMapping("/user/product/save/{order_number}/{store_name}/{product_name}/{quantity}")
    public String AddProductToOrder(@PathVariable("order_number") Integer order_number, @PathVariable("store_name") String store_name, @PathVariable("product_name") String product_name, @PathVariable("quantity") Integer quantity) throws Exception {
        OrderDao orderDao = new OrderDao();
        ProductsOrderDao productsOrderDao = new ProductsOrderDao();
        ProductsDao productsDao = new ProductsDao();
        StoreDao storeDao = new StoreDao();
        int productid;
        List<Products> Products = productsDao.findAll();
        List<Integer> orders = GetAllOrdersOfClient();
        for (int j = 0; j < orders.size(); j++) {
            if (order_number.equals(orders.get(j))) {
                int orderid = orderDao.findOrderByNumber(order_number).GetId();
                for (int k = 0; k < Products.size(); k++) {
                    if (Products.get(k).GetName().equals(product_name) && Products.get(k).GetStoreId() == storeDao.findStoreByName(store_name).GetId()) {
                        productid = Products.get(k).GetId();
                        ProductsOrder productsOrder = new ProductsOrder(productid, orderid, quantity);
                        productsOrderDao.save(productsOrder);
                        return "Товар добавлен в заказ.";
                    }
                }
            }
        }
        return "Товар не добавлен в заказ.";
    }

    @GetMapping("/user/order/status/{order_number}")
    public String GetOrderStatus(@PathVariable("order_number") Integer order_number) {
        UserDao userDao = new UserDao();
        OrderDao orderDao = new OrderDao();
        List<Integer> orders = GetAllOrdersOfClient();
        for (int j = 0; j < orders.size(); j++) {
            if (order_number.equals(orders.get(j))) {
                return orderDao.findOrderByNumber(order_number).GetStatus();
            }
        }
        return ("Это заказ неавторизированного пользователя, или заказа нет в базе данных.");
    }

    @GetMapping("/store/{id}")
    public String GetStoreById(@PathVariable("id") int id) {
        StoreDao storeDao = new StoreDao();
        return storeDao.findStoreById(id).toString();
    }

    @PutMapping("/admin/store/save/{name}")
    public String SaveStore(@PathVariable("name") String name) {
        StoreDao storeDao = new StoreDao();
        Store store = new Store(name);
        storeDao.save(store);
        return "Магазин создан. Вот информация о нём: " + store.toString();
    }

    @PutMapping("/admin/client/save/{name}/{surname}")
    public String SaveClient(@PathVariable("name") String name, @PathVariable("surname") String surname) {
        ClientDao clientDao = new ClientDao();
        Client client = new Client(name, surname);
        clientDao.save(client);
        return "Человек добавлен в базу данных. Вот информация о нём: " + client.toString();
    }

    @PutMapping("/admin/user/save/{clientid}/{username}/{password}")
    public String SaveUser(@PathVariable("clientid") int clientid, @PathVariable("username") String username, @PathVariable("password") String password) {
        UserDao userDao = new UserDao();
        User user = new User(clientid, username, password);
        userDao.save(user);
        return "Пользователь добавлен в базу данных. Вот информация о нём: " + user.toString() + " Авторизация данного человека возможна только после полного перезапуска программы.";
    }

    @PutMapping("/admin/seller/save/{storeid}/{sellername}/{password}")
    public String SaveSeller(@PathVariable("storeid") int storeid, @PathVariable("sellername") String sellername, @PathVariable("password") String password) {
        SellerDao sellerDao = new SellerDao();
        Seller seller = new Seller(storeid, sellername, password);
        sellerDao.save(seller);
        return "Продавец добавлен в базу данных. Вот информация о нём: " + seller.toString() + " Авторизация данного человека возможна только после полного перезапуска программы.";
    }

    @DeleteMapping("/admin/store/delete/{id}")
    public String DeleteStoreById(@PathVariable("id") int id) {
        StoreDao storeDao = new StoreDao();
        storeDao.delete(storeDao.findStoreById(id));
        return "Магазин был удалён. У него был id: " + id;
    }

    @PatchMapping("/admin/store/update/{id}/{name}")
    public String UpdateStoreById(@PathVariable("id") int id, @PathVariable("name") String name) {
        StoreDao storeDao = new StoreDao();
        Store store = storeDao.findStoreById(id);
        Store newstore = new Store(id, name);
        storeDao.update(newstore);
        return "Название магазина было обновлено. Раньше он был в базе данных: " + store.toString() + ". Теперь он стал: " + newstore.toString();
    }

    @PatchMapping("/admin/user/update/{id}/{clientid}/{username}/{password}")
    public String UpdateUser(@PathVariable("id") int id, @PathVariable("clientid") int clientid, @PathVariable("username") String username, @PathVariable("password") String password) {
        UserDao userDao = new UserDao();
        User user = userDao.findUserById(id);
        User newuser = new User(id, clientid, username, password);
        userDao.update(newuser);
        return "Данные пользователя были обновлены. Раньше он был в базе данных: " + user.toString() + ". Теперь он стал: " + newuser.toString() + " Авторизация данного человека возможна только после полного перезапуска программы.";
    }

    @PatchMapping("/admin/seller/update/{id}/{storeid}/{sellername}/{password}")
    public String UpdateSeller(@PathVariable("id") int id, @PathVariable("storeid") int storeid, @PathVariable("sellername") String sellername, @PathVariable("password") String password) {
        SellerDao sellerDao = new SellerDao();
        Seller seller = sellerDao.findSellerById(id);
        Seller newseller = new Seller(id, storeid, sellername, password);
        sellerDao.update(newseller);
        return "Данные продавца были обновлены. Раньше он был в базе данных: " + seller.toString() + ". Теперь он стал: " + newseller.toString() + " Авторизация данного человека возможна только после полного перезапуска программы.";
    }

    @GetMapping("/user")
    public String user() {
        return "У вас есть доступ к командам пользователя.";
    }

    @GetMapping("/seller")
    public String seller() {
        return "У вас есть доступ к командам продавца.";
    }

    @GetMapping("/loginname")
    public String GetLoginname() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping("/admin")
    public String admin() {
        return "У вас есть доступ к командам админа.";
    }

    @GetMapping("/")
    public ArrayList<String> info() {
        ArrayList<String> info = new ArrayList<String>();
        info.add("Команды, которые доступны без авторизации:");
        info.add("Авторизоваться: http://localhost:8089/login");
        info.add("Узнать логин текущего авторизованного пользователя: http://localhost:8089/loginname");
        info.add("Выйти из аккаунта, если в него был вход; потом можно переавторизоваться: http://localhost:8089/logout");
        info.add("Вывод всех магазинов из базы данных: http://localhost:8089/store");
        info.add("Вывод информации о магазине из базы данных по id: http://localhost:8089/store/{id}");
        info.add("Узнать текущую дату, установленную в программе: http://localhost:8089/getdate");

        info.add("                                                                                                                                                                                                                                                    ");
        info.add("                                                                                                                                                                                                                                                    ");
        info.add("Команды, доступные пользователю и админу:");
        info.add("Узнать, есть ли доступ к командам пользователя: http://localhost:8089/user");
        info.add("Вывод информации обо всех продуктах из базы данных: http://localhost:8089/user/products");
        info.add("Вывод информации обо всех продуктах в данном магазине: http://localhost:8089/user/products/{store_name}");
        info.add("Вывод информации об авторизованном пользователе (если его username не admin и не user) из базы данных: http://localhost:8089/user/info");
        info.add("Узнать номера всех заказов пользователя (если его username не admin и не user) из базы данных: http://localhost:8089/user/orders");
        info.add("Узнать обо всех товарах и итоговой стоимости заказа пользователя (если его username не admin и не user) из базы данных: http://localhost:8089/user/orderproducts/{order_number}");
        info.add("Создать новый исходно пустой заказ (если его username не admin и не user) из базы данных: http://localhost:8089/user/order/save/{order_number}");
        info.add("Добавить товар в заказ (если его username не admin и не user) из базы данных: http://localhost:8089/user/product/save/{order_number}/{store_name}/{product_name}/{quantity}");
        info.add("Узнать статус заказа (если его username не admin и не user) из базы данных: http://localhost:8089/user/order/status/{order_number}");

        info.add("                                                                                                                                                                                                                                                    ");
        info.add("                                                                                                                                                                                                                                                    ");
        info.add("Команды, доступные продавцу и иногда админу:");
        info.add("Узнать, есть ли доступ к командам продавца: http://localhost:8089/seller");
        info.add("Вывод информации об авторизованном продавце (если его sellername не admin и не seller) из базы данных: http://localhost:8089/seller/info");
        info.add("Вывод информации о товарах в магазине продавца (если его sellername не admin и не seller) из базы данных: http://localhost:8089/seller/products");
        info.add("Вывод номеров всех заказов в магазине продавца (если его sellername не admin и не seller) из базы данных: http://localhost:8089/seller/orders");
        info.add("Вывод информации о заказе по номеру заказа (если sellername не admin и не seller) из базы данных: http://localhost:8089/seller/orderinfo/{order_number}");
        info.add("Изменить статус заказа по номеру (если sellername не admin и не seller) из базы данных: http://localhost:8089/seller/orderstatus/{order_number}/{order_status}");
        info.add("Вывод информации о клиенте по номеру заказа (если sellername не admin и не seller) из базы данных: http://localhost:8089/seller/clientbyorder/{order_number}");
        info.add("Узнать данные о продуктах в заказе (если sellername не admin и не seller) из базы данных: http://localhost:8089/seller/orderproducts/{order_number}");

        info.add("                                                                                                                                                                                                                                                    ");
        info.add("                                                                                                                                                                                                                                                    ");
        info.add("Команды, доступные админу:");
        info.add("Узнать, есть ли доступ к командам админа: http://localhost:8089/admin");
        info.add("Вывод всех клиентов из базы данных: http://localhost:8089/admin/client");
        info.add("Вывод всех продавцов из базы данных: http://localhost:8089/admin/sellers");
        info.add("Вывод данных всех пользователей: http://localhost:8089/admin/users");
        info.add("Изменить текущую дату в системе: http://localhost:8089/admin/setdate/{date}");
        info.add("Сохранить нового человека в базу данных по имени и фамилии: http://localhost:8089/admin/client/save/{name}/{surname}");
        info.add("Сохранить новый магазин в базу данных по названию: http://localhost:8089/admin/store/save/{name}");
        info.add("Сохранить нового пользователя в базу данных по id человека, логину и паролю: http://localhost:8089/admin/user/save/{clientid}/{username}/{password}");
        info.add("Сохранить нового продавца в базу данных по id магазина, имени продавца и паролю: http://localhost:8089/admin/seller/save/{storeid}/{sellername}/{password}");
        info.add("Удалить существующий магазин в базе данных по id: http://localhost:8089/admin/store/delete/{id}");
        info.add("Обновить название существующего магазина в базе данных по id и новым данным магазина: http://localhost:8089/admin/store/update/{id}/{name}");
        info.add("Обновить данные существующего пользователя в базе данных по id и новым данным пользователя: http://localhost:8089/admin/user/update/{id}/{clientid}/{username}/{password}");
        info.add("Обновить данные существующего продавца в базе данных по id и новым данным продавца: http://localhost:8089/admin/seller/update/{id}/{storeid}/{sellername}/{password}");
        return info;
    }
}
