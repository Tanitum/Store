package myapp.Controllers;

import dao.*;
import model.Client;
import model.Seller;
import model.Store;
import model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Controller {

    @GetMapping("/store")
    public String Allstores() {
        StoreDao storeDao = new StoreDao();
        return storeDao.findAll().toString();
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

    @GetMapping("/user/info")
    public String GetUserInfo() {
        UserDao userDao = new UserDao();
        return userDao.findUserByusername(GetLoginname()).toString();
    }

    @GetMapping("/user/products")
    public String Allproducts() {
        ProductsDao productsDao = new ProductsDao();
        return productsDao.findAll().toString();
    }

    @GetMapping("/user/products/{store_name}")
    public String AllproductsOfStore(@PathVariable("store_name") String store_name) {
        StoreDao storeDao = new StoreDao();
        int storeid = storeDao.findStoreByName(store_name).GetId();
        ProductsDao productsDao = new ProductsDao();
        return productsDao.findProductsByStoreId(storeid).toString();
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

        info.add("                                                                                                                                                                                                                                                    ");
        info.add("                                                                                                                                                                                                                                                    ");
        info.add("Команды, доступные пользователю и админу:");
        info.add("Узнать, есть ли доступ к командам пользователя: http://localhost:8089/user");
        info.add("Вывод информации обо всех продуктах из базы данных: http://localhost:8089/user/products");
        info.add("Вывод информации обо всех продуктах в данном магазине: http://localhost:8089/user/products/{store_name}");
        info.add("Вывод информации об авторизованном пользователе (если его username не admin и не user) из базы данных: http://localhost:8089/user/info");


        info.add("                                                                                                                                                                                                                                                    ");
        info.add("                                                                                                                                                                                                                                                    ");
        info.add("Команды, доступные продавцу и админу:");
        info.add("Узнать, есть ли доступ к командам продавца: http://localhost:8089/seller");
        info.add("Вывод информации об авторизованном продавце (если его sellername не admin и не seller) из базы данных: http://localhost:8089/seller/info");

        info.add("                                                                                                                                                                                                                                                    ");
        info.add("                                                                                                                                                                                                                                                    ");
        info.add("Команды, доступные админу:");
        info.add("Узнать, есть ли доступ к командам админа: http://localhost:8089/admin");
        info.add("Вывод всех клиентов из базы данных: http://localhost:8089/admin/client");
        info.add("Вывод всех продавцов из базы данных: http://localhost:8089/admin/sellers");
        info.add("Вывод данных всех пользователей: http://localhost:8089/admin/users");
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
