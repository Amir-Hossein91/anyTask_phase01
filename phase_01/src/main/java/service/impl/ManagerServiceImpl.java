package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Manager;
import repository.impl.ManagerRepositoryImpl;

public class ManagerServiceImpl extends BaseServiceImpl<ManagerRepositoryImpl, Manager> {

    public ManagerServiceImpl(ManagerRepositoryImpl repository) {
        super(repository);
    }
}
