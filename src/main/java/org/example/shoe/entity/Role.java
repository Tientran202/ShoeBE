package org.example.shoe.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;
    private String name;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
    private List<User> users;

    public Role() {
    }

    public Role(Long role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }

    public Role(Long role_id) {
        this.role_id = role_id;
    }
    //get and set


    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
