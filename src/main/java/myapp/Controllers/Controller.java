package myapp.Controllers;

import dao.ClientDao;
import dao.ProductsDao;
import dao.StoreDao;
import model.Store;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @RequestMapping("/store")
    @GetMapping
    public String Allstores() {
        StoreDao store= new StoreDao();
        return store.findAll().toString();
    }

    @RequestMapping("/client")
    @GetMapping
    public String Allclients() {
        ClientDao client= new ClientDao();
        return client.findAll().toString();
    }

    @RequestMapping("/products")
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

    @RequestMapping("/store/save/{name}")
    @PostMapping
    public String SaveStore(@PathVariable("name") String name) {
        StoreDao storeDao= new StoreDao();
        Store store=new Store(name);
        storeDao.save(store);
        return "Магазин создан. Вот информация о нём: "+ store.toString();
    }

    @RequestMapping("/store/delete/{id}")
    @PostMapping
    public String DeleteStoreById(@PathVariable("id") int id) {
        StoreDao storeDao= new StoreDao();
        storeDao.delete(storeDao.findStoreById(id));
        return "Магазин был удалён. У него был id: " + id;
    }

    @RequestMapping("/store/update/{id}/{name}")
    @PostMapping
    public String UpdateStoreById(@PathVariable("id") int id, @PathVariable("name") String name) {
        StoreDao storeDao= new StoreDao();
        Store store=storeDao.findStoreById(id);
        Store newstore =new Store(id,name);
        storeDao.update(newstore);
        return "Название магазина было обновлено. Раньше он был в базе данных: " + store.toString()+ ". Теперь он стал: "+newstore.toString();
    }
}
