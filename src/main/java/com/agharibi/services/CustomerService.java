package com.agharibi.services;

import com.agharibi.commands.CustomerForm;
import com.agharibi.domain.Customer;

public interface CustomerService extends CRUDService<Customer> {
    Customer saveOrUpdateCustomerForm(CustomerForm customerForm);

}
