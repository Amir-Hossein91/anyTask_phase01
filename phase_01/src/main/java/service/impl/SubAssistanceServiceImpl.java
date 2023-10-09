package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.SubAssistance;
import repository.impl.SubAssistanceRepositoryImpl;

public class SubAssistanceServiceImpl extends BaseServiceImpl<SubAssistanceRepositoryImpl, SubAssistance> {

    public SubAssistanceServiceImpl(SubAssistanceRepositoryImpl repository) {
        super(repository);
    }
}
