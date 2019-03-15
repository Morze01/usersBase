package model;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(String name, String login, String password, String role) {
        this.name = name;
        this.password = password;
        this.login = login;
        this.role = role;
        this.id = (long)(new Random()).nextInt();
    }

    public User(Long id, String name, String password, String login, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
