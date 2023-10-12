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

////////////////////////////////////////////////////////////GENERAL:
        //1) register
//        personService.register();

        //2) login
//        personService.login("amir","amir1234");

        //3) change password
//        personService.changePassword("amir00","amir123456789","amir1234");

        //4) Everyone can see the list of assistance(Technicians must be active)
//        printer.printListWithoutSelect(assistanceService.seeAssistances("akbar"));

////////////////////////////////////////////////////////////MANAGER:

        //5) Manager can add assistance category
//        assistanceService.addAssistance("amir","Cleaning and hygiene");

        //6) Manager can add sub-assistance title
//        subAssistanceService.addSubAssistance("amir","Cleaning and hygiene","spraying");

        //7) Manager add a technician to a subAssistance
//        technicianService.addTechnicianToSubAssistance("amir","akbar","spraying","Cleaning and hygiene");

        //8) Manager remove a technician from a subAssistance
//        technicianService.removeTechnicianFromSubAssistance("amir","ali","checkup","car maintenance");

        //9) Manager can see the list of sub-assistance and their technicians and other details
//        printer.printListWithoutSelect(subAssistanceService.seeSubAssistances("omid"));

        //10) Manager can change the description of a sub-assistance
//        subAssistanceService.changeDescription("amir","Home Appliances","audiovisual equipment","description changed by manager");

        //11) Manager can change the base price of a sub-assistance
//        subAssistanceService.changeBasePrice("amir","Home Appliances","audiovisual equipment",4000000);

        //12) Manager can see the unapproved technicians and they'll become "PEDNING"
//        List<String> technicians = technicianService.seeUnapprovedTechnicians("amir");
//        if(technicians != null){
//            for(String s: technicians)
//                System.out.println(s);
//        }

        //13) Manager can see the list of technicians who are approved but deactivated
//        List<String> technicians = technicianService.seeDeactivatedTechnicians("amir");
//        if(technicians != null){
//            for(String s: technicians)
//                System.out.println(s);
//        }


////////////////////////////////////////////////////////////CUSTOMER:

        //14) Customer can see the list of sub-assistances
//        printer.printResult("All services",subAssistanceService.showSubAssistances("amir"));

        //15) Customer makes an order
//        orderService.makeOrder("alireza","Cleaning and hygiene","spraying");


////////////////////////////////////////////////////////////TECHNICIAN:

        //16) Technician can see list of related orders which are in waiting condition (if profile is active)
//        printer.printListWithoutSelect(technicianService.findRelativeOrders("akbar"));

        //17) Technician can make a suggestion to an order related to him (if profile is active)
        technicianService.sendTechnicianSuggestion("akbar",802);

    }


}
