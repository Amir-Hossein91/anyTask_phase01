package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Technician;
import repository.impl.TechnicianRepositoryImpl;

public class TechnicianServiceImpl extends BaseServiceImpl<TechnicianRepositoryImpl, Technician> {

    public TechnicianServiceImpl(TechnicianRepositoryImpl repository) {
        super(repository);
    }
}
