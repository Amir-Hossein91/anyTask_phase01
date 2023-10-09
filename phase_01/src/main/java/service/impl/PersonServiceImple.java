package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Person;
import repository.impl.PersonRepositoryImpl;

public class PersonServiceImple extends BaseServiceImpl<PersonRepositoryImpl, Person> {

    public PersonServiceImple(PersonRepositoryImpl repository) {
        super(repository);
    }
}
