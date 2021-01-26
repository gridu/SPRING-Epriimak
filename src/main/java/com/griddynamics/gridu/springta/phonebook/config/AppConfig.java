package com.griddynamics.gridu.springta.phonebook.config;

import com.griddynamics.gridu.springta.phonebook.model.Record;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.griddynamics.gridu.springta.phonebook")
public class AppConfig {

    @Bean(name = "default_data")
    public List<Record> defaultData() {
        List<Record> data = new LinkedList<>();
        data.add(new Record("Alex", "+111"));
        data.add(new Record("Billy", new HashSet<>(Arrays.asList("+79213215566", "+79213215567", "+79213215568"))));
        data.add(new Record("Samantha", new HashSet<>(Arrays.asList("5456786", "3648824"))));
        return data;
    }
}