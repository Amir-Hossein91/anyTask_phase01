package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Person;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Person> {

    public PersonRepositoryImpl(Class<Person> className) {
        super(className);
    }
}
