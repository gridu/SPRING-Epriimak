package com.griddynamics.gridu.springta.phonebook.controller;

import com.griddynamics.gridu.springta.phonebook.exception.NoSuchDataException;
import com.griddynamics.gridu.springta.phonebook.model.Record;
import com.griddynamics.gridu.springta.phonebook.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/customers")
public class PhoneBookController {

    private final PhoneBookService phoneBook;

    public PhoneBookController(@Qualifier("service") PhoneBookService phoneBook) {
        this.phoneBook = phoneBook;
    }

    @GetMapping(value = "")
    public String message() {
        return "Welcome!";
    }

    @GetMapping(value = "/getAllRecords",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Record> getAllRecords() {
        System.out.println("Get all");

        return phoneBook.findAllRecords();
    }

    @GetMapping(value = "/getRecordByName/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Record getRecordByName(@PathVariable("name") String name) {
        System.out.println("Get");

        return phoneBook.findRecordByName(name);
    }

    @PostMapping(value = "/addRecord",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Record addRecord(@RequestBody Map<String, String> body) {
        System.out.println("Post");

        String name = body.get("name");
        String phone = body.get("phones");
        return phoneBook.addRecord(name, phone);
    }

    @PutMapping(value = "updateRecordByName/{name}")
    public Record updateRecordByName(@PathVariable String name, @RequestParam String phone) {
        System.out.println("Put");

        return phoneBook.addRecord(name, phone);
    }

    @DeleteMapping(value = "deleteRecordByName/{name}")
    public void deleteRecordByName(@PathVariable String name) throws NoSuchDataException {
        System.out.println("Delete");

        phoneBook.removeRecordByName(name);
    }
}
