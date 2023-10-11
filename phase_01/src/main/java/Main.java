import connection.Connection;
import entity.*;
import service.impl.*;
import utility.ApplicationContext;
import utility.Printer;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static EntityManager entityManager = Connection.entityManager;
    public static Scanner input = ApplicationContext.input;
    public static Printer printer = ApplicationContext.printer;
    public static AssistanceServiceImpl assistanceService = ApplicationContext.assistanceService;
    public static CustomerServiceImpl customerService = ApplicationContext.customerService;
    public static ManagerServiceImpl managerService = ApplicationContext.managerService;
    public static OrderServiceImpl orderService = ApplicationContext.orderService;
    public static PersonServiceImple personService = ApplicationContext.personService;
    public static SubAssistanceServiceImpl subAssistanceService = ApplicationContext.subAssistanceService;
    public static TechnicianServiceImpl technicianService = ApplicationContext.technicianService;

    public static void main(String[] args) {
        //1) register
//        personService.register();

        //2) login
//        personService.login("amir","amir1234");

        //3) change password
//        personService.changePassword("amir00","amir123456789","amir1234");

        //4) Manager can add assistance category
//        assistanceService.addAssistance("amir","Cleaning and hygiene");

        //5) Manager can add sub-assistance title
//        subAssistanceService.addSubAssistance("amir","Cleaning and hygiene","spraying");

        //6) Manager add a technician to a subAssistance
//        technicianService.addTechnicianToSubAssistance("amir","ali","Kitchen appliances","Home Appliances");

        //7) Manager remove a technician from a subAssistance
//        technicianService.removeTechnicianFromSubAssistance("amir","ali","checkup","car maintenance");

        //8) Everyone can see the list of assistance
//        printer.printListWithoutSelect(assistanceService.findAll().stream().map(Object::toString).toList());

        //9) Manager can see the list of sub-assistance and their technicians
//        printer.printListWithoutSelect(subAssistanceService.seeSubAssistances("amir"));

        //10) Manager can change the description of a sub-assistance
//        subAssistanceService.changeDescription("amir","Home Appliances","audiovisual equipment","description changed by manager");

        //11) Manager can change the base price of a sub-assistance
//        subAssistanceService.changeBasePrice("amir","Home Appliances","audiovisual equipment",4000000);
    }


}
