package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Manager;
import entity.Person;
import entity.Technician;
import exceptions.NotFoundException;
import repository.impl.PersonRepositoryImpl;
import repository.impl.TechnicianRepositoryImpl;
import service.PersonService;
import utility.ApplicationContext;
import utility.Constants;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class PersonServiceImple extends BaseServiceImpl<PersonRepositoryImpl, Person> implements PersonService {
    private ManagerServiceImpl managerService;
    private CustomerServiceImpl customerService;
    private static TechnicianServiceImpl technicianService;

    public PersonServiceImple(PersonRepositoryImpl repository) {
        super(repository);
        managerService = ApplicationContext.managerService;
        customerService = ApplicationContext.customerService;
        TechnicianRepositoryImpl technicianRepository = new TechnicianRepositoryImpl(Technician.class);
        technicianService = new TechnicianServiceImpl(technicianRepository);
//        technicianService = ApplicationContext.technicianService;
    }

    public Person specifyPerson(){
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
        return Person.builder().firstName(firstname).lastName(lastname).email(email).username(username)
                .password(password).registrationDate(registrationDate).build();
    }

    public void changePassword(String username, String oldPassword, String newPassword){
        Person fetched = findByUsername(username);
        if(fetched != null) {
            try {
                if (!fetched.getPassword().equals(oldPassword))
                    throw new IllegalArgumentException(Constants.INCORRECT_PASSWORD);
                fetched.setPassword(newPassword);
                saveOrUpdate(fetched);
                printer.printMessage("password changed successfully");
            } catch (IllegalArgumentException e) {
                printer.printError(e.getMessage());
            }
        }
    }

    @Override
    public Person findByUsername(String username) {
        try {
            return repository.findByUsername(username).orElseThrow(()->new NotFoundException(Constants.INVALID_USERNAME));
        } catch (RuntimeException | NotFoundException e) {
            printer.printError(e.getMessage());
            return null;
        }
    }

    public Person register(){
        System.out.println("Rolls:");
        printer.printListWithSelect(List.of("Manager", "Technician", "Customer"));
        int choice = input.nextInt();
        input.nextLine();
        switch (choice){
            case 1 -> {
                Manager manager = managerService.specifyManager();
                if(manager == null){
                    printer.printError("This organization already has a defined manager");
                    return null;
                }
                return managerService.saveOrUpdate(manager);
            }
            case 2 -> {
                Path path = ApplicationContext.path;
                if(!technicianService.validateImage(path))
                    return null;
                Technician technician = technicianService.specifyTechnician(path);
                if(technician == null)
                    return null;
                return technicianService.saveOrUpdate(technician);
            }
            case 3 -> {
                return customerService.saveOrUpdate(customerService.specifyCustomer());
            }
            default -> {
                return saveOrUpdate(specifyPerson());
            }
        }
    }

    public void login(String username, String password){
        Person fetched = findByUsername(username);
        if(fetched != null){
            try {
                if (!fetched.getPassword().equals(password))
                    throw new IllegalArgumentException(Constants.INCORRECT_USERNAME_PASSWORD);
                printer.printMessage("Hello " + fetched.getFirstName() + ", you are a " + fetched.getClass().getSimpleName() + " here!");
            } catch (IllegalArgumentException e) {
                printer.printError(e.getMessage());
            }
        }
    }
}
