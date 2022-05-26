package model;

import javax.persistence.*;

@Entity
@Table(name = "clientorder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "clientid")
    private int clientid;

    @Column(name = "number")
    private int number;

    @Column(name = "status")
    private String status;

    @Column(name = "orderdate")
    private String orderdate;

    public Order(int clientid, int number, String status, String orderdate) {
        this.clientid = clientid;
        this.number = number;
        this.status = status;
        this.orderdate = orderdate;
    }

    public Order(int id, int clientid, int number, String status, String orderdate) {
        this.id = id;
        this.clientid = clientid;
        this.number = number;
        this.status = status;
        this.orderdate = orderdate;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return id + ";" + clientid + ";" + number + ";" + status + ";" + orderdate;
    }

    public int GetId() {
        return id;
    }

    public int GetClientId() {
        return clientid;
    }

    public int GetNumber() {
        return number;
    }

    public String GetStatus() {
        return status;
    }

    public String GetOrderdate() {
        return orderdate;
    }

    public String GetOrderInfoForSeller() {
        return id + ";" + number + ";" + status + ";" + orderdate;
    }

}