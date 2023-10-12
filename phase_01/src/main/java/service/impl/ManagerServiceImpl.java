package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Manager;
import repository.impl.ManagerRepositoryImpl;
import service.ManagerService;

import java.time.LocalDateTime;

public class ManagerServiceImpl extends BaseServiceImpl<ManagerRepositoryImpl, Manager> implements ManagerService {

    public ManagerServiceImpl(ManagerRepositoryImpl repository) {
        super(repository);
    }

    public Manager specifyManager(){
        if(!doesManagerExist()) {
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
            LocalDateTime registrationDate = LocalDateTime.now();
            return Manager.builder().firstName(firstname).lastName(lastname).email(email).username(username)
                    .password(password).registrationDate(registrationDate).build();
        }
        return null;
    }

    public boolean doesManagerExist(){
        return repository.doesManagerExist();
    }
}
