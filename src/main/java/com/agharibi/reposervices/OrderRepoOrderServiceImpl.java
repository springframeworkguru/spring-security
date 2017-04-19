package com.agharibi.reposervices;

import com.agharibi.domain.Order;
import com.agharibi.repositories.OrderRepository;
import com.agharibi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class OrderRepoOrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;

    @Override
    public List<?> listAll() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository.findOne(id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        return orderRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.delete(id);
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
