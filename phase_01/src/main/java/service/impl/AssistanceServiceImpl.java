package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Assistance;
import repository.impl.AssistanceRepositoryImpl;
import service.AssistanceService;

public class AssistanceServiceImpl extends BaseServiceImpl<AssistanceRepositoryImpl, Assistance> implements AssistanceService {

    public AssistanceServiceImpl(AssistanceRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public boolean doesAssistanceExist(String assistanceName) {
        return repository.doesAssistanceExist(assistanceName);
    }
}
