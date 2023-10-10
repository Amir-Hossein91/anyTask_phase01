package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.SubAssistance;
import repository.impl.SubAssistanceRepositoryImpl;
import service.SubAsssistanceService;

public class SubAssistanceServiceImpl extends BaseServiceImpl<SubAssistanceRepositoryImpl, SubAssistance> implements SubAsssistanceService {

    public SubAssistanceServiceImpl(SubAssistanceRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public boolean doesSubAssistanceExist(String title, long assistanceId) {
        return repository.doesSubAssistanceExist(title, assistanceId);
    }

//    public void specifySubAssistance(){
//        printer.getInput();
//    }
}
