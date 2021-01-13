import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project.config.AppConfig;
import project.config.WebInitializer;
import project.controller.PhoneBookController;
import project.entity.Person;
import project.exception.NoSuchDataException;
import project.service.PhoneBookService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WebInitializer.class, AppConfig.class})
@WebMvcTest(PhoneBookController.class)
public class PhoneBookServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhoneBookService phoneBookMock;

    @Test
    public void findAllPersonsAndGetOkStatus() throws Exception {
        List<Person> data = new LinkedList<>();
        data.add(new Person("Ann", "333-33-33"));
        data.add(new Person("Sam", "888-33-33"));

        BDDMockito.given(phoneBookMock.findAllPersons())
                .willReturn(data);

        mockMvc.perform(
                get("/v1/customers/getAllRecords")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("*").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("*").isNotEmpty())
                .andExpect(content().string(new Gson().toJson(data)));
    }

    @Test
    public void findPersonByExistedNameAndGetOkStatus() throws Exception {
        String name = "Alexa";
        String phone = "555-55-55";
        Person person = new Person(name, phone);

        BDDMockito.given(phoneBookMock.findPersonByName(name))
                .willReturn(person);

        mockMvc.perform(
                get("/v1/customers/getRecordByName/{name}", name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("phones").value(phone));
    }

    @Test
    public void findPersonByNotExistedNameAndGetOkStatus() throws Exception {
        String name = "Ann";
        Person emptyPerson = new Person();

        BDDMockito.given(phoneBookMock.findPersonByName(name))
                .willReturn(emptyPerson);

        mockMvc.perform(
                get("/v1/customers/getRecordByName/{name}", name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(emptyPerson.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("phones").value(emptyPerson.getPhones()));
    }

    @Test
    public void addPersonInfoAndGetOkStatus() throws Exception {
        String name = "Alexa";
        String phone = "++111-11-11";
        Person person = new Person(name, phone);

        Map<String, String> body = new HashMap<>();
        body.put("name", name);
        body.put("phones", phone);

        BDDMockito.given(phoneBookMock.addPersonInfo(name, phone))
                .willReturn(person);

        mockMvc.perform(post("/v1/customers/addRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(body)))
                .andExpect(status().isOk())
                .andExpect(content().string(new Gson().toJson(person)));
    }

    @Test
    public void removePersonByExistedNameAndGetOkStatus() throws Exception {
        String name = "Alexa";

        BDDMockito.given(phoneBookMock.removePersonByName(name))
                .willReturn("{}");

        mockMvc.perform(delete("/v1/customers/deleteRecordByName/{name}", name))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void removePersonByNotExistedNameAndGetInternalErrorStatus() throws Exception {
        String name = "Alexa";

        BDDMockito.given(phoneBookMock.removePersonByName(name))
                .willThrow(new NoSuchDataException("No such data"));

        mockMvc.perform(delete("/v1/customers/deleteRecordByName/{name}", name))
                .andExpect(status().is5xxServerError());
    }
}