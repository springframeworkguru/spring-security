package com.agharibi.repositories;


import com.agharibi.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer>{
}
