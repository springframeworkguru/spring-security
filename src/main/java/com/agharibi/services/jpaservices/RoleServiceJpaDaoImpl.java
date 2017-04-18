package com.agharibi.services.jpaservices;

import com.agharibi.domain.security.Role;
import com.agharibi.services.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class RoleServiceJpaDaoImpl extends AbstractJpaDaoService implements RoleService {

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Role.class, id);
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Role role = em.merge(domainObject);
        em.getTransaction().commit();
        return role;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Role.class, id));
        em.getTransaction().commit();
    }
}
