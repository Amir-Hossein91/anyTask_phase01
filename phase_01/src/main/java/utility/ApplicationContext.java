package utility;

import entity.*;
import repository.impl.*;
import service.impl.*;

import java.util.Scanner;

public class ApplicationContext {
    public static final Printer printer;
    public static final Scanner input;
    private static final AssistanceRepositoryImpl assistanceRepository;
    private static final CustomerRepositoryImpl customerRepository;
    private static final ManagerRepositoryImpl managerRepository;
    private static final OrderRepositoryImpl orderRepository;
    private static final PersonRepositoryImpl personRepository;
    private static final SubAssistanceRepositoryImpl subAssistanceRepository;
    private static final TechnicianRepositoryImpl technicianRepository;
    public static final AssistanceServiceImpl assistanceService;
    public static final CustomerServiceImpl customerService;
    public static final ManagerServiceImpl managerService;
    public static final OrderServiceImpl orderService;
    public static final PersonServiceImple personService;
    public static final SubAssistanceServiceImpl subAssistanceService;
    public static final TechnicianServiceImpl technicianService;

    static{
        printer = new Printer();
        input = new Scanner(System.in);
        assistanceRepository = new AssistanceRepositoryImpl(Assistance.class);
        assistanceService = new AssistanceServiceImpl(assistanceRepository);
        customerRepository = new CustomerRepositoryImpl(Customer.class);
        customerService = new CustomerServiceImpl(customerRepository);
        managerRepository = new ManagerRepositoryImpl(Manager.class);
        managerService = new ManagerServiceImpl(managerRepository);
        orderRepository = new OrderRepositoryImpl(Order.class);
        orderService = new OrderServiceImpl(orderRepository);
        personRepository = new PersonRepositoryImpl(Person.class);
        personService = new PersonServiceImple(personRepository);
        subAssistanceRepository = new SubAssistanceRepositoryImpl(SubAssistance.class);
        subAssistanceService = new SubAssistanceServiceImpl(subAssistanceRepository);
        technicianRepository = new TechnicianRepositoryImpl(Technician.class);
        technicianService = new TechnicianServiceImpl(technicianRepository);
    }
}
