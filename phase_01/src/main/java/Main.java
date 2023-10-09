import entity.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SubService sub1 = SubService.builder().title("zoroof").basePrice(1000).build();
        SubService sub2 = SubService.builder().title("zamin").basePrice(2000).build();
        SubService sub3 = SubService.builder().title("divar").basePrice(500).build();
        SubService sub4 = SubService.builder().title("pelle").basePrice(1000).build();

        List<SubService> subServices = List.of(sub1,sub2,sub3,sub4);

        Service service1 = Service.builder().title("Khanegi").subServices(subServices).build();
        Service service2 = Service.builder().title("omoomi").subServices(subServices).build();

        ServiceContainer.addService(new Manager(),service1);
        ServiceContainer.addService(new Customer(),service2);
        System.out.println(ServiceContainer.getServices());
    }
}
