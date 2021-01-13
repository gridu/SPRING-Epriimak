package project.repository;

import org.springframework.stereotype.Repository;
import project.entity.Person;
import project.exception.NoSuchDataException;

import java.util.*;

@Repository
public class InMemoryRepositoryIml implements InMemoryRepository {

    private List<Person> data;

    public InMemoryRepositoryIml() {
        this.data = new LinkedList<>();
    }

    public InMemoryRepositoryIml(List<Person> data) {
        this.data = new LinkedList<>(data);
    }

    @Override
    public List<Person> findAllPersons() {
        return new LinkedList<>(this.data);
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
