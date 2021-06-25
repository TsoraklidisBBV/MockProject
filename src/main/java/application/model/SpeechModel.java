package application.model;

import java.util.Date;

public class SpeechModel {
    public SpeechModel(String speaker, String title, Date date, Integer words) {
        super();
        this.speaker = speaker;
        this.title = title;
        this.date = date;
        this.words = words;
    }
    String speaker;
    String title;
    Date date;
    Integer words;



    public String getSpeaker() {
        return speaker;
    }
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Integer getWords() {
        return words;
    }
    public void setWords(Integer words) {
        this.words = words;
    }

}
