package com.griddynamics.gridu.springta.phonebook.repository;

import com.griddynamics.gridu.springta.phonebook.exception.NoSuchDataException;
import com.griddynamics.gridu.springta.phonebook.model.Record;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("repository")
public class InMemoryRepositoryImpl implements InMemoryRepository {

    private List<Record> data;

    public InMemoryRepositoryImpl(@Qualifier("default_data") List<Record> data) {
        this.data = data;
    }

    public List<Record> findAllRecords() {
        return data;
    }

    public Record findRecord(String name) {
        return data.stream().filter(record -> record.getName().equals(name)).findFirst().orElseGet(Record::new);
    }

    public Record addRecord(String name, String phone) {
        Record person;
        if (data.stream().anyMatch(r -> r.getName().equals(name))) {
            person = data.stream().filter(r -> r.getName().equals(name)).findFirst().get();
            person.getPhones().add(phone);
        } else {
            String[] phonesStringArray = phone.split(",");
            Set<String> phonesSet = new HashSet<>(Arrays.asList(phonesStringArray));
            person = new Record(name, phonesSet);
            data.add(person);
        }

        return person;
    }

    public void removeRecord(String name) throws NoSuchDataException {
        if (data.stream().noneMatch(record -> record.getName().equals(name))) {
            throw new NoSuchDataException("Record with name: " + name + " not exists");
        }

        Record existedRecord = data.stream().filter(record -> record.getName().equals(name)).findFirst().get();
        if (existedRecord.getPhones().isEmpty())
            throw new NoSuchDataException("Record with name: " + name + " has not phone");

        data.remove(existedRecord);
    }
}
