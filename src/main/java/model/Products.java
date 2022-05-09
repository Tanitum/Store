package model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "storeid")
    private int storeid;
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    public Products(int storeid, String name, double price) {
        this.storeid = storeid;
        this.name = name;
        this.price = price;
    }

    public Products(int id, int storeid, String name, double price) {
        this.id = id;
        this.storeid = storeid;
        this.name = name;
        this.price = price;
    }

    public Products() {
    }

    @Override
    public String toString() {
        return id + ";" + storeid + ";" + name + ";" + price;
    }

    public int GetId() {
        return id;
    }

    public int GetStoreId() {
        return storeid;
    }

    public String GetName() {
        return name;
    }

    public double GetPrice() {
        return price;
    }

}