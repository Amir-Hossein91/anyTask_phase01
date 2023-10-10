package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.SubAssistance;
import repository.SubAssistanceRepository;

import javax.persistence.Query;

public class SubAssistanceRepositoryImpl extends BaseRepositoryImpl<SubAssistance> implements SubAssistanceRepository {

    public SubAssistanceRepositoryImpl(Class<SubAssistance> className) {
        super(className);
    }

    @Override
    public boolean doesSubAssistanceExist(String title, long assistancId) {
        String queryLine = "from SubAssistance where title=:t and assistance=:a";
        Query query = entityManager.createQuery(queryLine);
        query.setParameter("t",title);
        query.setParameter("a",assistancId);
        return !query.getResultList().isEmpty();
    }
}
