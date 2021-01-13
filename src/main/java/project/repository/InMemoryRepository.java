package project.repository;

import project.entity.Person;
import project.exception.NoSuchDataException;

import java.util.List;

public interface InMemoryRepository {
    List<Person> findAllPersons();

    Person findPerson(String name);

    Person addPerson(String name, String phone);

    void removePerson(String name) throws NoSuchDataException;
}
