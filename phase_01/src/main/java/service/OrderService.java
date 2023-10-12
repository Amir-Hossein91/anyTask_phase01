package service;

import entity.Customer;
import entity.Order;
import entity.Technician;
import entity.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findRelatedOrders(Technician technician);
    void sendTechnicianSuggestion(Technician technician,Order order);
    List<Order> findByCustomer(Customer customer);
}
