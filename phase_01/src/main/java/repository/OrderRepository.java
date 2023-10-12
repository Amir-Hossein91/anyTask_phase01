package repository;

import entity.Customer;
import entity.Order;
import entity.Technician;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<List<Order>> findRelatedOrders(Technician technician);
    Optional<List<Order>> findByCustomer(Customer customer);
}
