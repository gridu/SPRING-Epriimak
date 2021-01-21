package project.repository;

import project.exception.NoSuchDataException;
import project.model.Person;

import java.util.List;

public interface InMemoryRepository {

    List<Person> findAllPersons();

    Person findPerson(String name);

    Person addPerson(String name, String phone);

    void removePerson(String name) throws NoSuchDataException;
}
