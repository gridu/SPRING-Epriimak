package com.griddynamics.gridu.springta.phonebook.service;

import com.griddynamics.gridu.springta.phonebook.model.Record;
import com.griddynamics.gridu.springta.phonebook.exception.NoSuchDataException;

import java.util.List;

public interface PhoneBookService {

    List<Record> findAllRecords();

    Record addRecord(String name, String phone);

    Record findRecordByName(String name);

    boolean removeRecordByName(String name) throws NoSuchDataException;
}
