package project.service;

import project.exception.NoSuchDataException;
import project.model.Person;

import java.util.List;

public interface PhoneBookService {

    List<Person> findAllPersons();

    Person addPersonInfo(String name, String phone);

    Person findPersonByName(String name);

    String removePersonByName(String name) throws NoSuchDataException;
}
