package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Technician;
import entity.enums.TechnicianStatus;
import exceptions.InvalidImageException;
import repository.impl.TechnicianRepositoryImpl;
import utility.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class TechnicianServiceImpl extends BaseServiceImpl<TechnicianRepositoryImpl, Technician> {

    public TechnicianServiceImpl(TechnicianRepositoryImpl repository) {
        super(repository);
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
}
