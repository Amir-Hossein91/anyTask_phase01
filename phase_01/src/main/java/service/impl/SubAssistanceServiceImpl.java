package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Assistance;
import entity.SubAssistance;
import repository.impl.SubAssistanceRepositoryImpl;
import service.SubAsssistanceService;

public class SubAssistanceServiceImpl extends BaseServiceImpl<SubAssistanceRepositoryImpl, SubAssistance> implements SubAsssistanceService {

    public SubAssistanceServiceImpl(SubAssistanceRepositoryImpl repository) {
        super(repository);
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
}
