package model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "storeid")
    private int storeid;
    @Column(name = "sellername")
    private String sellername;

    @Column(name = "password")
    private String password;

    public int GetSeller_id() {
        return id;
    }

    public String GetSeller_sellername() {
        return sellername;
    }

    public String GetSeller_password() {
        return password;
    }

    public Seller(int storeid, String sellername, String password) {
        this.storeid = storeid;
        this.sellername = sellername;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public Seller(int id, int storeid, String sellername, String password) {
        this.id = id;
        this.storeid = storeid;
        this.sellername = sellername;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public Seller() {
    }

    @Override
    public String toString() {
        return id + ";" + storeid + ";" + sellername;
    }

}