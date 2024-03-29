package model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "clientid")
    private int clientid;
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public int GetUser_id() {
        return id;
    }

    public int GetUserClientId() {
        return clientid;
    }

    public String GetUser_username() {
        return username;
    }

    public String GetUser_password() {
        return password;
    }

    public User(int clientid, String username, String password) {
        this.clientid = clientid;
        this.username = username;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public User(int id, int clientid, String username, String password) {
        this.id = id;
        this.clientid = clientid;
        this.username = username;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public User() {
    }

    @Override
    public String toString() {
        return id + ";" + clientid + ";" + username;
    }

}
