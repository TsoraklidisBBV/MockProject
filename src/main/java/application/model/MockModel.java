package application.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class MockModel {
    String name;
    String title;
    Date date;
    Integer words;
}
