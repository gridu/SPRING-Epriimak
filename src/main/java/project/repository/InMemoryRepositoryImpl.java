package project.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import project.exception.NoSuchDataException;
import project.model.Person;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("repository")
public class InMemoryRepositoryImpl implements InMemoryRepository {

    private List<Person> data;

    public InMemoryRepositoryImpl(@Qualifier("default_data") List<Person> data) {
        this.data = data;
    }

    @Override
    public List<Person> findAllPersons() {
        return data;
    }

    @Override
    public Person findPerson(String name) {
        return data.stream().filter(person -> person.getName().equals(name)).findFirst().orElseGet(Person::new);
    }

    @Override
    public Person addPerson(String name, String phone) {
        Person person;
        if (data.stream().anyMatch(p -> p.getName().equals(name))) {
            person = data.stream().filter(p -> p.getName().equals(name)).findFirst().get();
            person.getPhones().add(phone);
        } else {
            String[] phonesStringArray = phone.split(",");
            Set<String> phonesSet = new HashSet<>(Arrays.asList(phonesStringArray));
            person = new Person(name, phonesSet);
            data.add(person);
        }

        return person;
    }

    @Override
    public void removePerson(String name) throws NoSuchDataException {
        if (data.stream().noneMatch(person -> person.getName().equals(name))) {
            throw new NoSuchDataException("Record with name: " + name + " not exists");
        }

        Person existedPerson = data.stream().filter(person -> person.getName().equals(name)).findFirst().get();
        if (existedPerson.getPhones().isEmpty())
            throw new NoSuchDataException("Record with name: " + name + " has not phone");

        data.remove(existedPerson);
    }
}
