package entity;

import java.util.ArrayList;
import java.util.List;

public class ServiceContainer {
    private static List<Service> services = new ArrayList<>();

    public static void addService(Person person, Service service){
        if(person instanceof Manager)
            services.add(service);
    }

    public static List<String> getServices(){
        List<String> result = new ArrayList<>();
        StringBuilder subservices = new StringBuilder();
        for(Service s : services){
            for(SubService sub : s.getSubServices()) {
                subservices.append("\t* ").append(sub).append("\n");
            }

            result.add(s.getTitle() + ":\n" + subservices);
            subservices.setLength(0);
        }
        return result;
    }
}
