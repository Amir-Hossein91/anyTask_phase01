package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Order;
import entity.dto.OrderDTO;
import entity.Technician;
import repository.OrderRepository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl extends BaseRepositoryImpl<Order> implements OrderRepository {

    public OrderRepositoryImpl(Class<Order> className) {
        super(className);
    }

    public Optional<List<Order>> findRelatedOrders(Technician technician){
        String queryLine = """
                                select c from Technician a 
                                join a.subAssistances b 
                                join b.orders c 
                                where a=:i 
                                and b = c.subAssistance
                                and (c.orderStatus = 'WAITING_FOR_TECHNICIANS_SUGGESTIONS'
                                or c.orderStatus = 'CHOOSING_TECHNICIAN')
                                """;
        Query query = entityManager.createQuery(queryLine);
        query.setParameter("i",technician);
        return Optional.of((List<Order>)query.getResultList());
    }
}
