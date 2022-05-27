package model;

import javax.persistence.*;

@Entity
@Table(name = "productsorder")
public class ProductsOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "productid")
    private int productid;

    @Column(name = "orderid")
    private int orderid;

    @Column(name = "quantity")
    private int quantity;

    public ProductsOrder(int productid, int orderid, int quantity) {
        this.productid = productid;
        this.orderid = orderid;
        this.quantity = quantity;
    }

    public ProductsOrder(int id, int productid, int orderid, int quantity) {
        this.id = id;
        this.productid = productid;
        this.orderid = orderid;
        this.quantity = quantity;
    }

    public ProductsOrder() {
    }

    @Override
    public String toString() {
        return id + ";" + productid + ";" + orderid + ";" + quantity;
    }

    public int GetID() {
        return id;
    }

    public int GetProductId() {
        return productid;
    }

    public int GetOrderId() {
        return orderid;
    }

    public int GetQuantity() {
        return quantity;
    }

}