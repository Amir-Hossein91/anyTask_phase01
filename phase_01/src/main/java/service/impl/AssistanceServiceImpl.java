package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Assistance;
import entity.Manager;
import entity.Person;
import entity.Technician;
import exceptions.DeactivatedTechnicianException;
import exceptions.DuplicateAssistanceException;
import exceptions.NotFoundException;
import repository.impl.AssistanceRepositoryImpl;
import repository.impl.PersonRepositoryImpl;
import service.AssistanceService;
import utility.ApplicationContext;
import utility.Constants;

import javax.validation.constraints.Null;
import java.util.List;
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

    public List<String> seeAssistances(String personUsername){
        Person person = personService.findByUsername(personUsername);
        try {
            if(person == null)
                throw new NotFoundException(Constants.INVALID_USERNAME);
            if(person instanceof Technician && !((Technician) person).isActive())
                throw new DeactivatedTechnicianException(Constants.DEACTIVATED_TECHNICIAN);
            return findAll().stream().map(Object::toString).toList();
        } catch (NotFoundException | DeactivatedTechnicianException e) {
            printer.printError(e.getMessage());
            return List.of();
        }
    }
}
