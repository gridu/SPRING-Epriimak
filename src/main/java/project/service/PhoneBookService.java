package project.service;


import org.springframework.stereotype.Service;
import project.entity.Person;
import project.exception.NoSuchDataException;
import project.repository.InMemoryRepository;

import java.util.List;


@Service
public class PhoneBookService {

    private final InMemoryRepository repository;

    public PhoneBookService(InMemoryRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAllPersons() {
        return repository.findAllPersons();
    }

    public Person addPersonInfo(String name, String phone) {
        return repository.addPerson(name, phone);
    }

    public Person findPersonByName(String name) {
        return repository.findPerson(name);
    }

    public String removePersonByName(String name) throws NoSuchDataException {
        repository.removePerson(name);
        return "{}";
    }
}
