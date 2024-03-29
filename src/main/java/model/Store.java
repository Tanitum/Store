package model;


import javax.persistence.*;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    public Store(String name) {
        this.name = name;
    }

    public Store(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Store() {
    }

    @Override
    public String toString() {
        return id + ";" + name;
    }

    public int GetId() {
        return id;
    }

    public String GetName() {
        return name;
    }

}
