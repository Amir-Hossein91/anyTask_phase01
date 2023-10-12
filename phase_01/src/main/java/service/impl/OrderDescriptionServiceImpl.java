package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.OrderDescription;
import repository.impl.OrderDescriptionRepositoryImpl;

public class OrderDescriptionServiceImpl extends
        BaseServiceImpl<OrderDescriptionRepositoryImpl, OrderDescription> {

    public OrderDescriptionServiceImpl(OrderDescriptionRepositoryImpl repository) {
        super(repository);
    }

}
