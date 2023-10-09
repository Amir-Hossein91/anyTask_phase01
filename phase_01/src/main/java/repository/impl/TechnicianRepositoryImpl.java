package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Technician;

public class TechnicianRepositoryImpl extends BaseRepositoryImpl<Technician> {

    public TechnicianRepositoryImpl(Class<Technician> className) {
        super(className);
    }
}
