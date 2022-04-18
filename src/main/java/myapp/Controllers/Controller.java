package myapp.Controllers;

import dao.ClientDao;
import dao.ProductsDao;
import dao.StoreDao;
import dao.UserDao;
import model.Store;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Controller {
    @RequestMapping("/store")
    @GetMapping
    public String Allstores() {
        StoreDao store= new StoreDao();
        return store.findAll().toString();
    }

    @RequestMapping("/admin/client")
    @GetMapping
    public String Allclients() {
        ClientDao client= new ClientDao();
        return client.findAll().toString();
    }

    @RequestMapping("/admin/users")
    @GetMapping
    public String Allusers() {
        UserDao user= new UserDao();
        return user.findAll().toString();
    }

    @RequestMapping("/user/products")
    @GetMapping
    public String Allproducts() {
        ProductsDao products= new ProductsDao();
        return products.findAll().toString();
    }

    @RequestMapping("/store/{id}")
    @GetMapping
    public String GetStoreById(@PathVariable("id") int id) {
        StoreDao store= new StoreDao();
        return store.findStoreById(id).toString();
    }

    @RequestMapping("/admin/store/save/{name}")
    @PostMapping
    public String SaveStore(@PathVariable("name") String name) {
        StoreDao storeDao= new StoreDao();
        Store store=new Store(name);
        storeDao.save(store);
        return "Магазин создан. Вот информация о нём: "+ store.toString();
    }

    @RequestMapping("/admin/store/delete/{id}")
    @PostMapping
    public String DeleteStoreById(@PathVariable("id") int id) {
        StoreDao storeDao= new StoreDao();
        storeDao.delete(storeDao.findStoreById(id));
        return "Магазин был удалён. У него был id: " + id;
    }

    @RequestMapping("/admin/store/update/{id}/{name}")
    @PostMapping
    public String UpdateStoreById(@PathVariable("id") int id, @PathVariable("name") String name) {
        StoreDao storeDao= new StoreDao();
        Store store=storeDao.findStoreById(id);
        Store newstore =new Store(id,name);
        storeDao.update(newstore);
        return "Название магазина было обновлено. Раньше он был в базе данных: " + store.toString()+ ". Теперь он стал: "+newstore.toString();
    }

    @GetMapping("/user")
    public String user() {
        return "У вас есть доступ к командам пользователя.";
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
        info.add("Выйти из аккаунта, если в него был вход; потом можно переавторизоваться: http://localhost:8089/logout");
        info.add("Вывод всех магазинов из базы данных: http://localhost:8089/store");
        info.add("Вывод информации о магазине из базы данных по id: http://localhost:8089/store/{id}");

        info.add("                                                                                                                                                                                                                                                    ");
        info.add("                                                                                                                                                                                                                                                    ");
        info.add("Команды, доступные пользователю и админу:");
        info.add("Узнать, есть ли доступ к командам пользователя: http://localhost:8089/user");
        info.add("Вывод информации обо всех продуктах из базы данных: http://localhost:8089/user/products");

        info.add("                                                                                                                                                                                                                                                    ");
        info.add("                                                                                                                                                                                                                                                    ");
        info.add("Команды, доступные админу:");
        info.add("Узнать, есть ли доступ к командам админа: http://localhost:8089/admin");
        info.add("Вывод всех клиентов из базы данных: http://localhost:8089/admin/client");
        info.add("Вывод данных всех пользователей (логины и пароли): http://localhost:8089/admin/users");
        info.add("Сохранить новый магазин в базу данных по названию: http://localhost:8089/admin/store/save/{name}");
        info.add("Удалить существующий магазин в базе данных по id: http://localhost:8089/admin/store/delete/{id}");
        info.add("Обновить название существующего магазина в базе данных по id: http://localhost:8089/admin/store/update/{id}/{name}");
        return info;
    }
}
