package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Manager;
import repository.ManagerRepository;
import service.ManagerService;

import javax.persistence.Query;

public class ManagerRepositoryImpl extends BaseRepositoryImpl<Manager> implements ManagerRepository {

    public ManagerRepositoryImpl(Class<Manager> className) {
        super(className);
    }

    @Override
    public boolean doesManaderExist() {
        String queryLine = "from Person where isManager = true ";
        Query query = entityManager.createQuery(queryLine);
        return !query.getResultList().isEmpty();
    }
}
