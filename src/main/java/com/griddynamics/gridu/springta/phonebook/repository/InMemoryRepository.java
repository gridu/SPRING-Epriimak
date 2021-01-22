package com.griddynamics.gridu.springta.phonebook.repository;

import com.griddynamics.gridu.springta.phonebook.exception.NoSuchDataException;
import com.griddynamics.gridu.springta.phonebook.model.Record;

import java.util.List;

public interface InMemoryRepository {

    List<Record> findAllRecords();

    Record findRecord(String name);

    Record addRecord(String name, String phone);

    void removeRecord(String name) throws NoSuchDataException;
}
