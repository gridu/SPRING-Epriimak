package com.griddynamics.gridu.springta.phonebook.service;

import com.griddynamics.gridu.springta.phonebook.exception.NoSuchDataException;
import com.griddynamics.gridu.springta.phonebook.model.Record;
import com.griddynamics.gridu.springta.phonebook.repository.InMemoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("service")
public class PhoneBookServiceImpl implements PhoneBookService {

    private InMemoryRepository repository;

    public PhoneBookServiceImpl(@Qualifier("repository") InMemoryRepository repository) {
        this.repository = repository;
    }

    public List<Record> findAllRecords() {
        return repository.findAllRecords();
    }

    public Record addRecord(String name, String phone) {
        return repository.addRecord(name, phone);
    }

    public Record findRecordByName(String name) {
        return repository.findRecord(name);
    }

    public boolean removeRecordByName(String name) throws NoSuchDataException {
        repository.removeRecord(name);
        return true;
    }
}
