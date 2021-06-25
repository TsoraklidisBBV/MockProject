package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class SpeakerApplication {
    public static void main(String[] args) {
        new SpringApplication(SpeakerApplication.class).run(args);
    }
}
