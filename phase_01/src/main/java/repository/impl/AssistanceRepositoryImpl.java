package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Assistance;
import repository.AssistanceRepository;

import javax.persistence.Query;

public class AssistanceRepositoryImpl extends BaseRepositoryImpl<Assistance> implements AssistanceRepository {

    public AssistanceRepositoryImpl(Class<Assistance> className) {
        super(className);
    }

    @Override
    public boolean doesAssistanceExist(String assistanceName) {
        String queryLine = "from Assistance where title=:t";
        Query query = entityManager.createQuery(queryLine);
        query.setParameter("t",assistanceName);
        return !query.getResultList().isEmpty();
    }
}
