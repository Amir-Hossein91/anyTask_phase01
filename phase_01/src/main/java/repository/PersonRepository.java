package repository;

import entity.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findByUsername(String username);
}
