package com.agharibi.services;

import com.agharibi.domain.User;
import com.agharibi.services.CRUDService;


public interface UserService extends CRUDService<User> {

    User findByUsername(String username);
}
