package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.*;
import entity.enums.TechnicianStatus;
import exceptions.DeactivatedTechnicianException;
import exceptions.DuplicateTechnicianException;
import exceptions.InvalidImageException;
import exceptions.NotFoundException;
import repository.impl.TechnicianRepositoryImpl;
import utility.ApplicationContext;
import utility.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class TechnicianServiceImpl extends BaseServiceImpl<TechnicianRepositoryImpl, Technician> {

    private final PersonServiceImple personService;
    private final SubAssistanceServiceImpl subAssistanceService;
    private final AssistanceServiceImpl assistanceService;

    public TechnicianServiceImpl(TechnicianRepositoryImpl repository) {
        super(repository);
        personService = ApplicationContext.personService;
        subAssistanceService = ApplicationContext.subAssistanceService;
        assistanceService = ApplicationContext.assistanceService;
    }

    public Technician specifyTechnician(Path path){
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
        try {
            byte[] image = Files.readAllBytes(path);
            return Technician.builder().firstName(firstname).lastName(lastname).email(email).username(username)
                    .password(password).registrationDate(registrationDate).score(0).credit(0).isActive(true)
                    .technicianStatus(TechnicianStatus.NEW).subAssistances(List.of())
                    .image(image).isTechnician(true).build();
        } catch (IOException e) {
            printer.printError(e.getMessage());
            return null;
        }

    }

    public boolean validateImage(Path path){
        try {
            String pathString = path.toString();
            if(!pathString.endsWith(".jpg"))
                throw new InvalidImageException(Constants.INVALID_IMAGE_FORMAT);
            byte[] image = Files.readAllBytes(path);
            if(image.length > 307200)
                throw new InvalidImageException(Constants.INVALID_IMAGE_SIZE);
            return true;
        } catch (IOException | InvalidImageException e) {
            printer.printError(e.getMessage());
            return false;
        }
    }

    public void saveImageToDirectory(Path path,byte[] image){
        try {
            Files.write(path,image);
        } catch (IOException e) {
            printer.printError(Constants.IMAGE_NOT_SAVED_TO_DIRECTORY);
        }
    }

    public void addTechnicianToSubAssistance(String managerName, String technicianName,
                                             String subassistanceTitle,String assistanceTitle){
        Person manager = personService.findByUsername(managerName);
        if(manager instanceof Manager){
            try{
                Person technician = personService.findByUsername(technicianName);
                Assistance assistance = assistanceService.findAssistance(assistanceTitle);

                if(assistance == null)
                    throw new NotFoundException(Constants.ASSISTANCE_NOT_FOUND);

                SubAssistance subAssistance = subAssistanceService.findSubAssistance(subassistanceTitle,assistance);

                if(!(technician instanceof Technician) || subAssistance == null)
                    throw new NotFoundException(Constants.TECHINICIAN_OR_SUBASSISTANCE_NOT_FOUND);

                if(!((Technician) technician).isActive())
                    throw new DeactivatedTechnicianException(Constants.DEACTIVATED_TECHNICIAN);

                List<Technician> technicians = subAssistance.getTechnicians();
                if(technicians.contains(technician))
                    throw new DuplicateTechnicianException(Constants.DUPLICATE_TECHNICIAN_SUBASSISTANCE);

                technicians.add((Technician) technician);
                ((Technician) technician).setTechnicianStatus(TechnicianStatus.APPROVED);
                subAssistanceService.saveOrUpdate(subAssistance);

            } catch (NotFoundException | DeactivatedTechnicianException | DuplicateTechnicianException e) {
                printer.printError(e.getMessage());
            }
        }
        else
            printer.printError(("Only manager can add technicians to a sub-assistance"));
    }

    public void removeTechnicianFromSubAssistance(String managerName, String technicianName,
                                             String subassistanceTitle,String assistanceTitle){
        Person manager = personService.findByUsername(managerName);
        if(manager instanceof Manager){
            try{
                Person technician = personService.findByUsername(technicianName);
                Assistance assistance = assistanceService.findAssistance(assistanceTitle);

                if(assistance == null)
                    throw new NotFoundException(Constants.ASSISTANCE_NOT_FOUND);

                SubAssistance subAssistance = subAssistanceService.findSubAssistance(subassistanceTitle,assistance);

                if(!(technician instanceof Technician) || subAssistance == null)
                    throw new NotFoundException(Constants.TECHINICIAN_OR_SUBASSISTANCE_NOT_FOUND);

                List<Technician> technicians = subAssistance.getTechnicians();
                if(!technicians.contains(technician))
                    throw new NotFoundException(Constants.TECHNICIAN_NOT_IN_LIST);

                technicians.remove((Technician) technician);
                subAssistanceService.saveOrUpdate(subAssistance);

            } catch (NotFoundException e) {
                printer.printError(e.getMessage());
            }
        }
        else
            printer.printError(("Only manager can remove technicians from a sub-assistance"));
    }
}
