package service;

import entity.Person;

public interface PersonService {
    Person findByUsername(String username);
}
