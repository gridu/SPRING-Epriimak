import com.google.gson.Gson;
import com.griddynamics.gridu.springta.phonebook.config.AppConfig;
import com.griddynamics.gridu.springta.phonebook.config.WebInitializer;
import com.griddynamics.gridu.springta.phonebook.controller.PhoneBookController;
import com.griddynamics.gridu.springta.phonebook.exception.NoSuchDataException;
import com.griddynamics.gridu.springta.phonebook.service.PhoneBookServiceImpl;
import com.griddynamics.gridu.springta.phonebook.model.Record;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(PhoneBookController.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WebInitializer.class, AppConfig.class})
public class PhoneBookServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhoneBookServiceImpl phoneBookMock;

    @Test
    public void findAllRecordsAndGetOkStatus() throws Exception {
        List<Record> data = new LinkedList<>();
        data.add(new Record("Ann", "333-33-33"));
        data.add(new Record("Sam", "888-33-33"));

        when(phoneBookMock.findAllRecords())
                .thenReturn(data);

        mockMvc.perform(
                get("/v1/customers/getAllRecords")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("*").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("*").isNotEmpty())
                .andExpect(content().string(new Gson().toJson(data)));
    }

    @Test
    public void findRecordByExistedNameAndGetOkStatus() throws Exception {
        String name = "Alexa";
        String phone = "555-55-55";
        Record record = new Record(name, phone);

        when(phoneBookMock.findRecordByName(name))
                .thenReturn(record);

        mockMvc.perform(
                get("/v1/customers/getRecordByName/{name}", name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("phones").value(phone));
    }

    @Test
    public void findRecordByNotExistedNameAndGetOkStatus() throws Exception {
        String name = "Ann";
        Record emptyRecord = new Record();

       when(phoneBookMock.findRecordByName(name))
                .thenReturn(emptyRecord);

        mockMvc.perform(
                get("/v1/customers/getRecordByName/{name}", name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(emptyRecord.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("phones").value(emptyRecord.getPhones()));
    }

    @Test
    public void addRecordAndGetOkStatus() throws Exception {
        String name = "Alexa";
        String phone = "++111-11-11";
        Record record = new Record(name, phone);

        Map<String, String> body = new HashMap<>();
        body.put("name", name);
        body.put("phones", phone);

        when(phoneBookMock.addRecord(name, phone))
                .thenReturn(record);

        mockMvc.perform(post("/v1/customers/addRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(body)))
                .andExpect(status().isOk())
                .andExpect(content().string(new Gson().toJson(record)));
    }

    @Test
    public void removeRecordByExistedNameAndGetOkStatus() throws Exception {
        String name = "Alexa";

        when(phoneBookMock.removeRecordByName(name))
                .thenReturn(true);

        mockMvc.perform(delete("/v1/customers/deleteRecordByName/{name}", name))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void removeRecordByNotExistedNameAndGetInternalErrorStatus() throws Exception {
        String name = "Alexa";

        when(phoneBookMock.removeRecordByName(name))
                .thenThrow(new NoSuchDataException("No such data"));

        mockMvc.perform(delete("/v1/customers/deleteRecordByName/{name}", name))
                .andExpect(status().is5xxServerError());
    }
}