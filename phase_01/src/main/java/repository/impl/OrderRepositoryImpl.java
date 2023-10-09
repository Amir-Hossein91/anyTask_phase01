package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Order;

public class OrderRepositoryImpl extends BaseRepositoryImpl<Order> {

    public OrderRepositoryImpl(Class<Order> className) {
        super(className);
    }
}
