package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Order;
import repository.impl.OrderRepositoryImpl;

public class OrderServiceImpl extends BaseServiceImpl<OrderRepositoryImpl, Order> {

    public OrderServiceImpl(OrderRepositoryImpl repository) {
        super(repository);
    }
}
