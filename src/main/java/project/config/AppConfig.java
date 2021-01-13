package project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import project.entity.Person;
import project.repository.InMemoryRepository;
import project.repository.InMemoryRepositoryIml;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "project")
public class AppConfig {

    @Bean
    public List<Person> defaultData() {
        List<Person> data = new LinkedList<>();
        data.add(new Person("Alex", "+111"));
        data.add(new Person("Billy", new HashSet<>(Arrays.asList("+79213215566", "+79213215567", "+79213215568"))));
        data.add(new Person("Samantha", new HashSet<>(Arrays.asList("5456786", "3648824"))));
        return data;
    }

    @Bean(name = "repository")
    public InMemoryRepository inMemoryRepository(List<Person> defaultData) {
        return new InMemoryRepositoryIml(defaultData);
    }
}