package com.griddynamics.gridu.springta.phonebook.model;

import lombok.Data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
public class Record {

    private String name = null;
    private Set<String> phones = null;

    public Record(String name, Set<String> phones) {
        this.name = name;
        this.phones = phones;
    }

    public Record(String name, String phone) {
        this.name = name;
        this.phones = new HashSet<>(Arrays.asList(phone));
    }

    public Record() {
    }
}
