package myapp.Services;

import dao.ProductsDao;
import lombok.RequiredArgsConstructor;
import model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ServiceInflation {
    private static final String CRON = "*/10 * * * * *";

    @Scheduled(cron = CRON)
    public void ProductInflation() {
        ProductsDao productsDao = new ProductsDao();
        List<Products> list = productsDao.findAll();
        if (!list.isEmpty()) {
            list.forEach(product -> {
                double price = product.GetPrice();
                price = price * 1.00001;
                productsDao.update(new Products(product.GetId(), product.GetStoreId(), product.GetName(), price));
            });
        }
    }
}
