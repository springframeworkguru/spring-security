package com.agharibi.domain.security;

import com.agharibi.domain.AbstractDomain;
import com.agharibi.domain.User;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role extends AbstractDomain{

    private String role;

    @ManyToMany
    @JoinTable
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addUser(User user) {
        if(! this.users.contains(user))
            this.users.add(user);
        if (!user.getRoles().contains(this))
            user.getRoles().add(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getRoles().remove(this);
    }
}
