package com.agharibi.services;

import java.util.List;

/**
 * Created by Armen on 4/8/17.
 */
public interface CRUDService<T> {
    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
