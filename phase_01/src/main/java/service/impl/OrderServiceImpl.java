package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import com.github.mfathi91.time.PersianDate;
import entity.*;
import entity.dto.OrderDTO;
import entity.enums.OrderStatus;
import exceptions.NotFoundException;
import repository.impl.OrderRepositoryImpl;
import repository.impl.PersonRepositoryImpl;
import repository.impl.SubAssistanceRepositoryImpl;
import service.OrderService;
import utility.ApplicationContext;
import utility.Constants;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl extends BaseServiceImpl<OrderRepositoryImpl, Order> implements OrderService {

    private final PersonServiceImple personService;
    private final AssistanceServiceImpl assistanceService;
    private final SubAssistanceServiceImpl subAssistanceService;

    public OrderServiceImpl(OrderRepositoryImpl repository) {
        super(repository);
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl(Person.class);
        personService = new PersonServiceImple(personRepository);
//        personService = ApplicationContext.personService;
        assistanceService = ApplicationContext.assistanceService;
        SubAssistanceRepositoryImpl subAssistanceRepository = new SubAssistanceRepositoryImpl(SubAssistance.class);
        subAssistanceService = new SubAssistanceServiceImpl(subAssistanceRepository);
//        subAssistanceService = ApplicationContext.subAssistanceService;
    }

    public void makeOrder(String customerUsername, String assistanceTitle, String subAssistanceTitle){
        Person person = personService.findByUsername(customerUsername);
        if( person instanceof Customer){
            try{
                Assistance assistance = assistanceService.findAssistance(assistanceTitle);
                if(assistance == null)
                    throw new NotFoundException(Constants.ASSISTANCE_NOT_FOUND);

                SubAssistance subAssistance = subAssistanceService.findSubAssistance(subAssistanceTitle,assistance);
                if(subAssistance == null)
                    throw new NotFoundException(Constants.NO_SUCH_SUBASSISTANCE);

                OrderDescription orderDescription = createOrderDescription(subAssistance);

                Order order = Order.builder().subAssistance(subAssistance).customer((Customer) person)
                        .orderRegistrationDateAndTime(LocalDateTime.now()).orderDescription(orderDescription)
                        .orderStatus(OrderStatus.WAITING_FOR_TECHNICIANS_SUGGESTIONS)
                        .technicianScore(1).build();
                saveOrUpdate(order);
            } catch (NotFoundException | DateTimeException | IllegalArgumentException e) {
                printer.printError(e.getMessage());
            }
        }
        else
            printer.printError("Only a customer can make an order");
    }

    private OrderDescription createOrderDescription(SubAssistance subAssistance){
        long basePrice = subAssistance.getBasePrice();
        OrderDescription orderDescription = null;
        while(orderDescription == null){
            try{
                printer.getInput("Suggested price");
                long suggestedPrice = input.nextLong();
                input.nextLine();
                if(suggestedPrice < basePrice)
                    throw new IllegalArgumentException(Constants.INVALID_SUGGESTED_PRICE);
                printer.getInput("Desired year");
                int year = input.nextInt();
                input.nextLine();
                printer.getInput("Desired month");
                int month = input.nextInt();
                input.nextLine();
                printer.getInput("Desired day");
                int day = input.nextInt();
                input.nextLine();
                printer.getInput("Desired hour");
                int hour = input.nextInt();
                input.nextLine();
                printer.getInput("Desired minute");
                int minute = input.nextInt();
                input.nextLine();
                PersianDate persionDate = PersianDate.of(year,month,day);
                LocalDate gregorianDate = persionDate.toGregorian();
                LocalDateTime dateTime = gregorianDate.atTime(hour,minute);
                if(dateTime.isBefore(LocalDateTime.now()))
                    throw new DateTimeException(Constants.DATE_BEFORE_NOW);
                printer.getInput("Task details");
                String details = input.nextLine();
                printer.getInput("Address");
                String address = input.nextLine();
                orderDescription = OrderDescription.builder().customerSuggestedPrice(suggestedPrice)
                        .customerDesiredDateAndTime(dateTime).taskDetails(details).address(address).build();
            } catch (DateTimeException | IllegalArgumentException e){
                printer.printError(e.getMessage());
            }
        }
        return orderDescription;
    }

    public List<OrderDTO> findRelatedOrders(Technician technician){
        try{
            List<Order> fetchedOrders = repository.findRelatedOrders(technician).orElseThrow(
                    () -> new NotFoundException(Constants.NO_RELATED_ORDERS)
            );
            List<OrderDTO> orderDTOs = new ArrayList<>();
            for(Order o : fetchedOrders){
                OrderDTO orderDTO = OrderDTO.builder()
                        .orderId(o.getId())
                        .subAssistanceTitle(o.getSubAssistance().getTitle())
                        .assistanceTitle(o.getSubAssistance().getAssistance().getTitle())
                        .basePrice(o.getSubAssistance().getBasePrice())
                        .customerFirstname(o.getCustomer().getFirstName())
                        .customerLastname(o.getCustomer().getLastName())
                        .customerId(o.getCustomer().getId())
                        .orderDate(o.getOrderRegistrationDateAndTime())
                        .orderDescription(o.getOrderDescription()).build();
                orderDTOs.add(orderDTO);
            }
            return orderDTOs;

        } catch (NotFoundException e){
            printer.printError(e.getMessage());
            return List.of();
        }

    }

    @Override
    public void sendTechnicianSuggestion(Technician technician, Order order) {
        try{
            List<Order> orders = repository.findRelatedOrders(technician).orElseThrow(
                    () -> new NotFoundException(Constants.NO_RELATED_ORDERS)
            );
            if(!orders.contains(order))
                throw new NotFoundException(Constants.ORDER_IS_NOT_RELATED);
            TechnicianSuggestion technicianSuggestion = createTechnicianSuggestion(order);
            if(technicianSuggestion != null){
                order.getTechnicianSuggestions().add(technicianSuggestion);
                saveOrUpdate(order);
            }
        } catch (NotFoundException e){
            printer.printError(e.getMessage());
        }
    }

    private TechnicianSuggestion createTechnicianSuggestion(Order order){
        LocalDateTime customerDesiredDate = order.getOrderDescription().getCustomerDesiredDateAndTime();
        long basePrice = order.getSubAssistance().getBasePrice();
        TechnicianSuggestion technicianSuggestion = null;
        while (technicianSuggestion == null){
            try{
                printer.getInput("Suggested price");
                long suggestedPrice = input.nextLong();
                if(suggestedPrice < basePrice)
                    throw new IllegalArgumentException(Constants.INVALID_SUGGESTED_PRICE);
                printer.getInput("Suggested year");
                int year = input.nextInt();
                printer.getInput("Suggested month");
                int month = input.nextInt();
                printer.getInput("Suggested day");
                int day = input.nextInt();
                printer.getInput("Suggested hour");
                int hour = input.nextInt();
                printer.getInput("Suggested minute");
                int minute = input.nextInt();
                PersianDate persionDate = PersianDate.of(year,month,day);
                LocalDate gregorianDate = persionDate.toGregorian();
                LocalDateTime dateTime = gregorianDate.atTime(hour,minute);
                if(dateTime.isBefore(customerDesiredDate))
                    throw new DateTimeException(Constants.DATE_BEFORE_CUSTOMER_DESIRED);
                printer.getInput("Estimated task duration (hours)");
                int taskDuration = input.nextInt();
                input.nextLine();

                technicianSuggestion = TechnicianSuggestion.builder().order(order)
                        .DateAndTimeOfTechSuggestion(LocalDateTime.now()).techSuggestedPrice(suggestedPrice)
                        .techSuggestedDate(dateTime).taskEstimatedDuration(taskDuration).build();

            } catch (DateTimeException | IllegalArgumentException e){
                printer.printError(e.getMessage());
            }
        }
        return technicianSuggestion;
    }
}
