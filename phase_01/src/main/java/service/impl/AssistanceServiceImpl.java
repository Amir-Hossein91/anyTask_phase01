package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Assistance;
import repository.impl.AssistanceRepositoryImpl;

public class AssistanceServiceImpl extends BaseServiceImpl<AssistanceRepositoryImpl, Assistance> {

    public AssistanceServiceImpl(AssistanceRepositoryImpl repository) {
        super(repository);
    }
}
