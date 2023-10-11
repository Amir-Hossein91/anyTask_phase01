package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Assistance;
import entity.Manager;
import entity.Person;
import exceptions.DuplicateAssistanceException;
import repository.impl.AssistanceRepositoryImpl;
import repository.impl.PersonRepositoryImpl;
import service.AssistanceService;
import utility.ApplicationContext;
import utility.Constants;

import javax.validation.constraints.Null;
import java.util.Optional;

public class AssistanceServiceImpl extends BaseServiceImpl<AssistanceRepositoryImpl, Assistance> implements AssistanceService {

    private PersonServiceImple personService;

    public AssistanceServiceImpl(AssistanceRepositoryImpl repository) {
        super(repository);
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl(Person.class);
        personService = new PersonServiceImple(personRepository);
//        personService = ApplicationContext.personService;
    }

    @Override
    public Assistance findAssistance(String assistanceName) {
        return repository.findAssistance(assistanceName).orElse(null);
    }

    public void addAssistance(String username, String assistanceName){
        Person person = personService.findByUsername(username);
        if(person instanceof Manager){
            try {
                if (findAssistance(assistanceName) != null)
                    throw new DuplicateAssistanceException(Constants.ASSISTANCE_ALREADY_EXISTS);
                Assistance assistance = Assistance.builder().title(assistanceName).build();
                saveOrUpdate(assistance);
            } catch (DuplicateAssistanceException e ){
                printer.printError(e.getMessage());
            }
        }
        else
            printer.printError("Only manager can add assistance categories");
    }
}
