package com.agharibi.reposervices;

import com.agharibi.commands.CustomerForm;
import com.agharibi.converters.CustomerFormToCustomer;
import com.agharibi.domain.Customer;
import com.agharibi.repositories.CustomerRepositroy;
import com.agharibi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class CustomerRepoServiceImpl implements CustomerService {

    private CustomerRepositroy customerRepositroy;
    private CustomerFormToCustomer customerFormToCustomer;

    @Override
    public List<?> listAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepositroy.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer getById(Integer id) {
        return customerRepositroy.findOne(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return customerRepositroy.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        customerRepositroy.delete(id);
    }

    @Override
    public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
        Customer customer = customerFormToCustomer.convert(customerForm);

        if(customer.getUser().getId() != null) {
            Customer existingCustomer = getById(customer.getId());
            customer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }
        return saveOrUpdate(customer);
    }

    @Autowired
    public void setCustomerRepositroy(CustomerRepositroy customerRepositroy) {
        this.customerRepositroy = customerRepositroy;
    }

    @Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
    }
}
