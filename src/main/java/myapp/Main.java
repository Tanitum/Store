package myapp;

import dao.*;
import model.Order;
import model.Products;
import model.ProductsOrder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class Main {

  /*  public static void main(String[] args) throws  Exception{

        StoreDao store= new StoreDao();
        //System.out.println(store.findStoreById(1).toString());
        System.out.println(store.findAll());
        //store.save(new Store("SuperMall"));
        //System.out.println(store.findStoreById(3).toString());
        //store.delete(store.findStoreById(3));
        //store.update(new Store(4,"BestMall"));

        ClientDao client= new ClientDao();
        //System.out.println(client.findClientById(1).toString());
        //client.save(new Client("Ivan","Ivanova"));
        System.out.println(client.findAll());
        //client.update(new Client(2,"Ivan","Ivanov"));
       // client.save(new Client("Ivan","Sokolov"));
        //client.delete(client.findClientById(3));

        ProductsDao products= new ProductsDao();
       // products.save(new Products(1,"Tea",500));
        //System.out.println(products.findProductsById(1).toString());
        //products.update(new Products(1,1,"Butter",140));
       // products.save(new Products(2,"Bread",120));
        System.out.println(products.findAll());
       // products.delete(products.findProductsById(4));


        OrderDao order= new OrderDao();
       // order.save(new Order(12872,"Сompleted","11.04.2022"));
      //  System.out.println(order.findOrderById(1).toString());
        //order.update(new Order(1,14381,"Сompleted","12.04.2022"));
        //order.save(new Order(13478,"InProgress","15.04.2022"));
        System.out.println(order.findAll());
        //order.delete(order.findOrderById(4));

        ProductsOrderDao productsorder= new ProductsOrderDao();
       // productsorder.save(new ProductsOrder(1,1,3));
        //System.out.println(productsorder.findProductsOrdersById(1).toString());
       // productsorder.update(new ProductsOrder(1,2,2,10));
        //productsorder.save(new ProductsOrder(2,3,15));
        System.out.println(productsorder.findAll());
       // productsorder.delete(productsorder.findProductsOrdersById(3));
    }
*/

           public static void main(String[] args) {
            SpringApplication.run(Main.class, args);
   }

}
