package project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import project.exception.NoSuchDataException;
import project.model.Person;
import project.repository.InMemoryRepository;

import java.util.List;


@Service("service")
public class PhoneBookServiceImpl implements PhoneBookService {

    private InMemoryRepository repository;

    public PhoneBookServiceImpl(@Qualifier("repository") InMemoryRepository repository) {
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
