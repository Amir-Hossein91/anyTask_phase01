package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Customer;
import repository.impl.CustomerRepositoryImpl;

public class CustomerServiceImpl extends BaseServiceImpl<CustomerRepositoryImpl, Customer> {

    public CustomerServiceImpl(CustomerRepositoryImpl repository) {
        super(repository);
    }
}
