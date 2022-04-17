package Controllers;
import java.util.concurrent.atomic.AtomicLong;

import dao.ClientDao;
import dao.ProductsDao;
import dao.StoreDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @RequestMapping("/store")
    public String Allstores() {
        StoreDao store= new StoreDao();
        return store.findAll().toString();
    }

    @RequestMapping("/client")
    public String Allclients() {
        ClientDao client= new ClientDao();
        return client.findAll().toString();
    }

    @RequestMapping("/products")
    public String Allproducts() {
        ProductsDao products= new ProductsDao();
        return products.findAll().toString();
    }
}
