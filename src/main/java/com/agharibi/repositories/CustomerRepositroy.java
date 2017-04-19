package com.agharibi.repositories;

import com.agharibi.domain.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepositroy extends CrudRepository<Customer, Integer> {
}
