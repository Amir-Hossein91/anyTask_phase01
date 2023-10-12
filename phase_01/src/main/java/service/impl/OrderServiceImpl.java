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
                printer.getInput("suggested price");
                long suggestedPrice = input.nextLong();
                input.nextLine();
                if(suggestedPrice < basePrice)
                    throw new IllegalArgumentException(Constants.INVALID_SUGGESTED_PRICE);
                printer.getInput("desired year");
                int year = input.nextInt();
                input.nextLine();
                printer.getInput("desired month");
                int month = input.nextInt();
                input.nextLine();
                printer.getInput("desired day");
                int day = input.nextInt();
                input.nextLine();
                printer.getInput("desired hour");
                int hour = input.nextInt();
                input.nextLine();
                printer.getInput("desired minute");
                int minute = input.nextInt();
                input.nextLine();
                PersianDate persionDate = PersianDate.of(year,month,day);
                LocalDate gregorianDate = persionDate.toGregorian();
                LocalDateTime dateTime = gregorianDate.atTime(hour,minute);
                if(dateTime.isBefore(LocalDateTime.now()))
                    throw new DateTimeException(Constants.DATE_BEFORE_NOW);
                printer.getInput("task details");
                String details = input.nextLine();
                printer.getInput("address");
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
}
