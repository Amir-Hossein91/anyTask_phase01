package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Customer;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer> {

    public CustomerRepositoryImpl(Class<Customer> className) {
        super(className);
    }
}
