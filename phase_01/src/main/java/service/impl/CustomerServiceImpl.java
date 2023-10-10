package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Customer;
import repository.impl.CustomerRepositoryImpl;

import java.time.LocalDate;

public class CustomerServiceImpl extends BaseServiceImpl<CustomerRepositoryImpl, Customer> {

    public CustomerServiceImpl(CustomerRepositoryImpl repository) {
        super(repository);
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
}
