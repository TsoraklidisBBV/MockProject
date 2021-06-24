package application.model;

import lombok.Data;
import java.util.Date;

@Data
public class SpeakerModel {
    String name;
    String title;
    Date date;
    Integer words;
}
