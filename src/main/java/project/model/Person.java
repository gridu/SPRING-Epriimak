package project.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Person {

    private String name = null;
    private Set<String> phones = null;

    public Person(String name, Set<String> phones) {
        this.name = name;
        this.phones = phones;
    }

    public Person(String name, String phone) {
        this.name = name;
        this.phones = new HashSet<>(Arrays.asList(phone));
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }
}
