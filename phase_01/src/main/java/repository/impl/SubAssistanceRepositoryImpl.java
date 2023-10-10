package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Assistance;
import entity.SubAssistance;
import repository.SubAssistanceRepository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

public class SubAssistanceRepositoryImpl extends BaseRepositoryImpl<SubAssistance> implements SubAssistanceRepository {

    public SubAssistanceRepositoryImpl(Class<SubAssistance> className) {
        super(className);
    }

    @Override
    public Optional<SubAssistance> findSubAssistance(String title, Assistance assistance) {
        String queryLine = "from SubAssistance where title=:t and assistance=:a";
        Query query = entityManager.createQuery(queryLine);
        query.setParameter("t",title);
        query.setParameter("a",assistance);
        try {
            return Optional.of((SubAssistance) query.getSingleResult());
        } catch (NoResultException e){
            return Optional.empty();
        }
    }
}
