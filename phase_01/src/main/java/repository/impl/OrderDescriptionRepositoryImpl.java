package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.OrderDescription;

public class OrderDescriptionRepositoryImpl extends BaseRepositoryImpl<OrderDescription> {

    public OrderDescriptionRepositoryImpl(Class<OrderDescription> className) {
        super(className);
    }

}
