package model;

import javax.persistence.*;

@Entity
@Table(name = "clientorder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "status")
    private String status;

    @Column(name = "orderdate")
    private String orderdate;

    public Order(int number, String status, String orderdate){
        this.number=number;
        this.status=status;
        this.orderdate=orderdate;
    }
    public Order(int id, int number, String status, String orderdate){
        this.id=id;
        this.number=number;
        this.status=status;
        this.orderdate=orderdate;
    }

    public Order() {
    }

    @Override
    public String toString(){
        return id+ ";"+number+ ";"+status+ ";"+orderdate;
    }

}