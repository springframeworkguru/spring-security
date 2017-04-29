package com.agharibi.services.mapServices;

import com.agharibi.domain.DomainObject;
import com.agharibi.domain.User;
import com.agharibi.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class UserServiceMapImpl extends AbstractMapService implements UserService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return (User) super.saveOrUpdate(domainObject);
    }

    @Override
    public User findByUsername(String username) {
        Optional<DomainObject> user = domainMap.values().stream().filter(new Predicate<DomainObject>() {
            @Override
            public boolean test(DomainObject domainObject) {
                User user = (User) domainObject;
                return user.getUsername().equals(username);
            }
        }).findFirst();
        return (User) user.get();
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}