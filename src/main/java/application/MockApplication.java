package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class MockApplication {
    public static void main(String[] args) {
        new SpringApplication(MockApplication.class).run(args);
    }
}
