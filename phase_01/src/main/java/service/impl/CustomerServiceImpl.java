package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Customer;
import entity.Order;
import entity.Person;
import entity.TechnicianSuggestion;
import entity.dto.TechnicianSuggestionDTO;
import entity.enums.OrderStatus;
import exceptions.NotFoundException;
import repository.impl.CustomerRepositoryImpl;
import repository.impl.OrderRepositoryImpl;
import repository.impl.PersonRepositoryImpl;
import repository.impl.TechnicianSuggestionRepositoryImpl;
import service.OrderService;
import utility.ApplicationContext;
import utility.Constants;

import java.time.LocalDate;
import java.util.List;

public class CustomerServiceImpl extends BaseServiceImpl<CustomerRepositoryImpl, Customer> {

    private final PersonServiceImple personService;
    private final OrderServiceImpl orderService;
    private final TechnicianSuggestionServiceImpl technicianSuggestionService;

    public CustomerServiceImpl(CustomerRepositoryImpl repository) {
        super(repository);
        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl(Order.class);
        orderService = new OrderServiceImpl(orderRepository);
//        orderService = ApplicationContext.orderService;
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl(Person.class);
        personService = new PersonServiceImple(personRepository);
//        personService = ApplicationContext.personService;
        TechnicianSuggestionRepositoryImpl technicianSuggestionRepository = new TechnicianSuggestionRepositoryImpl(TechnicianSuggestion.class);
        technicianSuggestionService = new TechnicianSuggestionServiceImpl(technicianSuggestionRepository);
//        technicianSuggestionService = ApplicationContext.technicianSuggestionService;
    }

    public Customer specifyCustomer(){
        printer.getInput("first name");
        String firstname = input.nextLine();
        printer.getInput("last name");
        String lastname = input.nextLine();
        printer.getInput("email");
        String email = input.nextLine();
        printer.getInput("user name");
        String username = input.nextLine();
        printer.getInput("password");
        String password = input.nextLine();
        LocalDate registrationDate = LocalDate.now();
        printer.getInput("initial credit");
        long credit = input.nextLong();
        input.nextLine();
        return Customer.builder().firstName(firstname).lastName(lastname).email(email).username(username)
                .password(password).registrationDate(registrationDate).credit(credit).isCustomer(true).build();
    }

    public List<String> seeTechnicianSuggestions(String customerUsername, long orderId){
        Person person = personService.findByUsername(customerUsername);
        if(person instanceof Customer){
            try{
                Order order = orderService.findById(orderId);

                if(order == null)
                    throw new NotFoundException(Constants.NO_SUCH_ORDER);

                if(!order.getCustomer().equals(person))
                    throw new NotFoundException(Constants.ORDER_NOT_BELONG_TO_CUSTOMER);

                if(!(order.getOrderStatus() == OrderStatus.WAITING_FOR_TECHNICIANS_SUGGESTIONS
                        || order.getOrderStatus() == OrderStatus.CHOOSING_TECHNICIAN))
                    throw new NotFoundException(Constants.SUGGESTION_NOT_AVAILABLE_IN_THIS_STATUS);

                List<TechnicianSuggestionDTO> technicianSuggestions = technicianSuggestionService.getSuggestionsOf(order);
                if(technicianSuggestions == null)
                    return List.of();

                if(order.getOrderStatus() == OrderStatus.WAITING_FOR_TECHNICIANS_SUGGESTIONS) {
                    order.setOrderStatus(OrderStatus.CHOOSING_TECHNICIAN);
                    orderService.saveOrUpdate(order);
                }
                return technicianSuggestions.stream().map(Object::toString).toList();
            } catch (NotFoundException e) {
                printer.printError(e.getMessage());
                return List.of();
            }

        }
        else {
            printer.printError("Only customers have access to this list");
            return List.of();
        }

    }
}
