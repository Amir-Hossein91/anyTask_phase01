package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Technician;
import exceptions.NotSavedException;
import repository.TechnicianRepository;
import utility.Constants;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class TechnicianRepositoryImpl extends BaseRepositoryImpl<Technician> implements TechnicianRepository {

    public TechnicianRepositoryImpl(Class<Technician> className) {
        super(className);
    }

    @Override
    public Optional<List<Technician>> saveOrUpdate(List<Technician> technicians) throws NotSavedException {
            for(Technician t: technicians){
                t = saveOrUpdate(t).orElseThrow(() -> new NotSavedException(Constants.COULD_NOT_SAVE_THE_TECHNICIAN_LIST));
            }
       return Optional.of(technicians);
    }

    public Optional<List<Technician>> findUnapproved(){
        String queryLine = "from Technician where technicianStatus != 'APPROVED'";
        Query query = entityManager.createQuery(queryLine);
        try {
            return Optional.of((List<Technician>) query.getResultList());
        } catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Technician>> findDeactivated() {
        String queryLine = "from Technician where technicianStatus = 'APPROVED' and isActive = false";
        Query query = entityManager.createQuery(queryLine);
        try {
            return Optional.of((List<Technician>) query.getResultList());
        } catch (NoResultException e){
            return Optional.empty();
        }
    }
}
