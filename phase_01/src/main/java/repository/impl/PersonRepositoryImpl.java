package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Person;
import repository.PersonRepository;

import javax.persistence.Query;
import java.util.Optional;


public class PersonRepositoryImpl extends BaseRepositoryImpl<Person> implements PersonRepository {

    public PersonRepositoryImpl(Class<Person> className) {
        super(className);
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        String queryLine = "from Person where username = :n";
        Query query = entityManager.createQuery(queryLine);
        query.setParameter("n",username);
        return Optional.of((Person) query.getSingleResult());
    }
}
