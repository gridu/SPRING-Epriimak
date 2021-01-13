package project.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.entity.Person;
import project.exception.NoSuchDataException;
import project.service.PhoneBookService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1/customers")
public class PhoneBookController {

    private final PhoneBookService phoneBook;

    public PhoneBookController(PhoneBookService phoneBook) {
        this.phoneBook = phoneBook;
    }

    @GetMapping(value = "")
    public String message() {//REST Endpoint.
        return "Welcome!";
    }

    @GetMapping(value = "/getAllRecords",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getAllPersons() {
        System.out.println("Get all");

        return phoneBook.findAllPersons();
    }

    @GetMapping(value = "/getRecordByName/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getPersonInfoByName(@PathVariable("name") String name) {
        System.out.println("Get");

        return phoneBook.findPersonByName(name);
    }

    @PostMapping(value = "/addRecord",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person addPersonInfo(@RequestBody Map<String, String> body) {
        System.out.println("Post");

        String name = body.get("name");
        String phone = body.get("phones");
        return phoneBook.addPersonInfo(name, phone);
    }

    @PutMapping(value = "updateRecordByName/{name}")
    public Person updatePersonInfoByName(@PathVariable String name, @RequestParam String phone) {
        System.out.println("Put");

        return phoneBook.addPersonInfo(name, phone);
    }

    @DeleteMapping(value = "deleteRecordByName/{name}")
    public void deletePersonByName(@PathVariable String name) throws NoSuchDataException {
        System.out.println("Delete");

        phoneBook.removePersonByName(name);
    }
}
