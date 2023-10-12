package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Manager;
import repository.ManagerRepository;

import javax.persistence.Query;

public class ManagerRepositoryImpl extends BaseRepositoryImpl<Manager> implements ManagerRepository {

    public ManagerRepositoryImpl(Class<Manager> className) {
        super(className);
    }

    @Override
    public boolean doesManagerExist() {
        String queryLine = "from Manager";
        Query query = entityManager.createQuery(queryLine);
        return !query.getResultList().isEmpty();
    }
}
