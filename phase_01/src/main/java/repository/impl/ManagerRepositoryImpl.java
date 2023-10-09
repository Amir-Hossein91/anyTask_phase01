package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Manager;

public class ManagerRepositoryImpl extends BaseRepositoryImpl<Manager> {

    public ManagerRepositoryImpl(Class<Manager> className) {
        super(className);
    }
}
