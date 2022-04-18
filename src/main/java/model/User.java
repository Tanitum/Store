package model;

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

    public int GetUser_id ()
    {
        return id;
    }
    public String GetUser_username ()
    {
        return username;
    }
    public String GetUser_password ()
    {
        return password;
    }

    public User(int clientid, String username, String password){
        this.clientid=clientid;
        this.username=username;
        this.password=password;
    }
    public User(int id, int clientid, String username,String password){
        this.id=id;
        this.clientid=clientid;
        this.username=username;
        this.password=password;
    }

    public User() {
    }

    @Override
    public String toString(){
        return id+ ";"+clientid+ ";"+username+ ";"+password;
    }

}
