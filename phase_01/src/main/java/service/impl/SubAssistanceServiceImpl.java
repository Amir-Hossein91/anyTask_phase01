package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Assistance;
import entity.Manager;
import entity.Person;
import entity.SubAssistance;
import exceptions.DuplicateSubAssistanceException;
import exceptions.NoSuchAsssistanceCategoryException;
import exceptions.NotFoundException;
import repository.impl.SubAssistanceRepositoryImpl;
import service.SubAsssistanceService;
import utility.ApplicationContext;
import utility.Constants;

import java.util.List;

public class SubAssistanceServiceImpl extends BaseServiceImpl<SubAssistanceRepositoryImpl, SubAssistance> implements SubAsssistanceService {

    private PersonServiceImple personService;
    private AssistanceServiceImpl assistanceService;

    public SubAssistanceServiceImpl(SubAssistanceRepositoryImpl repository) {
        super(repository);
        personService = ApplicationContext.personService;
        assistanceService = ApplicationContext.assistanceService;
    }

    @Override
    public SubAssistance findSubAssistance(String title, Assistance assistance) {
        return repository.findSubAssistance(title, assistance).orElse(null);
    }

    public SubAssistance specifySubAssistance(Assistance assistance, String title){
        printer.getInput("base price");
        long basePrice = input.nextLong();
        input.nextLine();
        printer.getInput("descriptions");
        String description = input.nextLine();
        return SubAssistance.builder().assistance(assistance).title(title).basePrice(basePrice).about(description).build();
    }

    public void addSubAssistance(String username, String assistanceTitle, String subAssistanceTitle){
        Person person = personService.findByUsername(username);
        if(person instanceof Manager){
            try{
                Assistance assistance = assistanceService.findAssistance(assistanceTitle);
                if(assistance == null)
                    throw new NoSuchAsssistanceCategoryException(Constants.NO_SUCH_ASSISTANCE_CATEGORY);
                if(findSubAssistance(subAssistanceTitle,assistance) != null)
                    throw new DuplicateSubAssistanceException(Constants.SUBASSISTANCE_ALREADY_EXISTS);
                saveOrUpdate(specifySubAssistance(assistance, subAssistanceTitle));
            } catch (DuplicateSubAssistanceException | NoSuchAsssistanceCategoryException e) {
                printer.printError(e.getMessage());
            }
        }
        else
            printer.printError(("Only manager can add sub-assistance titles"));
    }

    public List<String> seeSubAssistances (String managerName){
        Person person = personService.findByUsername(managerName);
        if(person instanceof Manager){
            return findAll().stream().map(Object::toString).toList();
        }
        else {
            printer.printError(("Only manager can see sub-assistance and their technicians"));
            return List.of();
        }
    }

    public void changeDescription(String managerUsername,String assistanceTitle, String subAssistanceTitle, String newDescription){
        Person person = personService.findByUsername(managerUsername);
        if(person instanceof Manager){
            try{
                Assistance assistance = assistanceService.findAssistance(assistanceTitle);
                if(assistance == null)
                    throw new NotFoundException(Constants.NO_SUCH_ASSISTANCE_CATEGORY);
                SubAssistance subAssistance = findSubAssistance(subAssistanceTitle,assistance);
                if(subAssistance== null)
                    throw new NotFoundException(Constants.NO_SUCH_SUBASSISTANCE);
                subAssistance.setAbout(newDescription);
                saveOrUpdate(subAssistance);
            } catch (NotFoundException e) {
                printer.printError(e.getMessage());
            }
        }
        else {
            printer.printError(("Only manager can change description of a sub-assistance"));
        }
    }

    public void changeBasePrice(String managerUsername,String assistanceTitle, String subAssistanceTitle, long basePrice){
        Person person = personService.findByUsername(managerUsername);
        if(person instanceof Manager){
            try{
                Assistance assistance = assistanceService.findAssistance(assistanceTitle);
                if(assistance == null)
                    throw new NotFoundException(Constants.NO_SUCH_ASSISTANCE_CATEGORY);
                SubAssistance subAssistance = findSubAssistance(subAssistanceTitle,assistance);
                if(subAssistance== null)
                    throw new NotFoundException(Constants.NO_SUCH_SUBASSISTANCE);
                subAssistance.setBasePrice(basePrice);
                saveOrUpdate(subAssistance);
            } catch (NotFoundException e) {
                printer.printError(e.getMessage());
            }
        }
        else {
            printer.printError(("Only manager can change description of a sub-assistance"));
        }
    }
}
