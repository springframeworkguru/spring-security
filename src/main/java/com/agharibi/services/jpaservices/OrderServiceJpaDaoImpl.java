package com.agharibi.services.jpaservices;

import com.agharibi.domain.Order;
import com.agharibi.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class OrderServiceJpaDaoImpl extends AbstractJpaDaoService implements OrderService {

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Order", Order.class).getResultList();
    }

    @Override
    public Order getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Order.class, id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Order order = em.merge(domainObject);
        em.getTransaction().commit();
        return order;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Order.class, id));
        em.getTransaction().commit();
    }
}
