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
    @RequestMapping("/store")
    @GetMapping
    public String Allstores() {
        StoreDao storeDao = new StoreDao();
        return storeDao.findAll().toString();
    }

    @RequestMapping("/admin/client")
    @GetMapping
    public String Allclients() {
        ClientDao clientDao = new ClientDao();
        return clientDao.findAll().toString();
    }

    @RequestMapping("/admin/users")
    @GetMapping
    public String Allusers() {
        UserDao userDao = new UserDao();
        return userDao.findAll().toString();
    }

    @RequestMapping("/admin/sellers")
    @GetMapping
    public String Allsellers() {
        SellerDao sellerDao = new SellerDao();
        return sellerDao.findAll().toString();
    }

    @RequestMapping("/seller/info")
    @GetMapping
    public String GetSellerInfo() {
        SellerDao sellerDao = new SellerDao();
        return sellerDao.findSellerBySellername(GetLoginname()).toString();
    }

    @RequestMapping("/user/info")
    @GetMapping
    public String GetUserInfo() {
        UserDao userDao = new UserDao();
        return userDao.findUserByusername(GetLoginname()).toString();
    }

    @RequestMapping("/user/products")
    @GetMapping
    public String Allproducts() {
        ProductsDao productsDao = new ProductsDao();
        return productsDao.findAll().toString();
    }

    @RequestMapping("/user/products/{store_name}")
    @GetMapping
    public String AllproductsOfStore(@PathVariable("store_name") String store_name) {
        StoreDao storeDao = new StoreDao();
        int storeid = storeDao.findStoreByName(store_name).GetId();
        ProductsDao productsDao = new ProductsDao();
        return productsDao.findProductsByStoreId(storeid).toString();
    }

    @RequestMapping("/store/{id}")
    @GetMapping
    public String GetStoreById(@PathVariable("id") int id) {
        StoreDao storeDao = new StoreDao();
        return storeDao.findStoreById(id).toString();
    }

    @RequestMapping("/admin/store/save/{name}")
    @PostMapping
    public String SaveStore(@PathVariable("name") String name) {
        StoreDao storeDao = new StoreDao();
        Store store = new Store(name);
        storeDao.save(store);
        return "Магазин создан. Вот информация о нём: " + store.toString();
    }

    @RequestMapping("/admin/client/save/{name}/{surname}")
    @PostMapping
    public String SaveClient(@PathVariable("name") String name, @PathVariable("surname") String surname) {
        ClientDao clientDao = new ClientDao();
        Client client = new Client(name, surname);
        clientDao.save(client);
        return "Человек добавлен в базу данных. Вот информация о нём: " + client.toString();
    }

    @RequestMapping("/admin/user/save/{clientid}/{username}/{password}")
    @PostMapping
    public String SaveUser(@PathVariable("clientid") int clientid, @PathVariable("username") String username, @PathVariable("password") String password) {
        UserDao userDao = new UserDao();
        User user = new User(clientid, username, password);
        userDao.save(user);
        return "Пользователь добавлен в базу данных. Вот информация о нём: " + user.toString() + " Авторизация данного человека возможна только после полного перезапуска программы.";
    }

    @RequestMapping("/admin/seller/save/{storeid}/{sellername}/{password}")
    @PostMapping
    public String SaveSeller(@PathVariable("storeid") int storeid, @PathVariable("sellername") String sellername, @PathVariable("password") String password) {
        SellerDao sellerDao = new SellerDao();
        Seller seller = new Seller(storeid, sellername, password);
        sellerDao.save(seller);
        return "Продавец добавлен в базу данных. Вот информация о нём: " + seller.toString() + " Авторизация данного человека возможна только после полного перезапуска программы.";
    }

    @RequestMapping("/admin/store/delete/{id}")
    @PostMapping
    public String DeleteStoreById(@PathVariable("id") int id) {
        StoreDao storeDao = new StoreDao();
        storeDao.delete(storeDao.findStoreById(id));
        return "Магазин был удалён. У него был id: " + id;
    }

    @RequestMapping("/admin/store/update/{id}/{name}")
    @PostMapping
    public String UpdateStoreById(@PathVariable("id") int id, @PathVariable("name") String name) {
        StoreDao storeDao = new StoreDao();
        Store store = storeDao.findStoreById(id);
        Store newstore = new Store(id, name);
        storeDao.update(newstore);
        return "Название магазина было обновлено. Раньше он был в базе данных: " + store.toString() + ". Теперь он стал: " + newstore.toString();
    }

    @RequestMapping("/admin/user/update/{id}/{clientid}/{username}/{password}")
    @PostMapping
    public String UpdateUser(@PathVariable("id") int id, @PathVariable("clientid") int clientid, @PathVariable("username") String username, @PathVariable("password") String password) {
        UserDao userDao = new UserDao();
        User user = userDao.findUserById(id);
        User newuser = new User(id, clientid, username, password);
        userDao.update(newuser);
        return "Данные пользователя были обновлены. Раньше он был в базе данных: " + user.toString() + ". Теперь он стал: " + newuser.toString() + " Авторизация данного человека возможна только после полного перезапуска программы.";
    }

    @RequestMapping("/admin/seller/update/{id}/{storeid}/{sellername}/{password}")
    @PostMapping
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

    @RequestMapping("/loginname")
    @GetMapping
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
