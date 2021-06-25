package application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import application.model.QueryResult;
import application.model.SpeechModel;
import application.util.SpeechListReader;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SpeakerServiceTest {

    @InjectMocks
    SpeakerService classUnderTest;

    @Mock
    SpeechListReader speechListReader;

    @Test
    public void testPositive() throws IOException, ParseException {

        // arrange
        List<SpeechModel> answer = new ArrayList<>();
        answer.add(new SpeechModel("Heinz Erhard", "lustig", new Date(),3232));
        answer.add(new SpeechModel("Heinz Erhard", "ganz lustig", new Date(),323232));
        answer.add(new SpeechModel("willi Mill", "nicht lustig", new Date(),32));
        Mockito.when(speechListReader.getSpeakerList(any(List.class))).thenReturn(answer);

        List<URL> urlList = new ArrayList<>();
        URL testUrl = new URL("http://localhost:8080");
        urlList.add(testUrl);
        // act
        QueryResult result = classUnderTest.getStatistics(urlList);

        // assert
        assertEquals("Heinz Erhard",result.getMostSpeeches());
    }

    @Test
    public void testNegativeMostSpeeches() throws IOException, ParseException {

        // arrange two speaker with same number of speeches
        List<SpeechModel> answer = new ArrayList<>();
        answer.add(new SpeechModel("Heinz Erhard", "lustig", new Date(),3232));
        answer.add(new SpeechModel("willi Mill", "nicht lustig", new Date(),32));
        Mockito.when(speechListReader.getSpeakerList(any(List.class))).thenReturn(answer);

        List<URL> urlList = new ArrayList<>();
        URL testUrl = new URL("http://localhost:8080");
        urlList.add(testUrl);
        // act
        QueryResult result = classUnderTest.getStatistics(urlList);

        // assert
        assertEquals(null ,result.getMostSpeeches());
    }

    @Test
    public void testPositiveMostSecurity() throws IOException, ParseException {

        // arrange two speaker with same number of speeches
        List<SpeechModel> answer = new ArrayList<>();
        answer.add(new SpeechModel("Heinz Erhard", "Innere Sicherheit", new Date(),3232));
        answer.add(new SpeechModel("willi Mill", "nicht lustig", new Date(),32));
        Mockito.when(speechListReader.getSpeakerList(any(List.class))).thenReturn(answer);

        List<URL> urlList = new ArrayList<>();
        URL testUrl = new URL("http://localhost:8080");
        urlList.add(testUrl);
        // act
        QueryResult result = classUnderTest.getStatistics(urlList);

        // assert
        assertEquals("Heinz Erhard" ,result.getMostSecurity());
    }

    @Test
    public void testManySpeakerHasTheTitleMostSecurity() throws IOException, ParseException {

        // arrange two speaker with same number of speeches
        List<SpeechModel> answer = new ArrayList<>();
        answer.add(new SpeechModel("Heinz Erhard", "Innere Sicherheit", new Date(),3232));
        answer.add(new SpeechModel("willi Mill", "Innere Sicherheit", new Date(),3232));
        Mockito.when(speechListReader.getSpeakerList(any(List.class))).thenReturn(answer);

        List<URL> urlList = new ArrayList<>();
        URL testUrl = new URL("http://localhost:8080");
        urlList.add(testUrl);
        // act
        QueryResult result = classUnderTest.getStatistics(urlList);

        // assert
        assertEquals(null ,result.getMostSecurity());
    }

    @Test
    public void testNoSpeakerHasTheTitleMostSecurity() throws IOException, ParseException {

        // arrange two speaker with same number of speeches
        List<SpeechModel> answer = new ArrayList<>();
        answer.add(new SpeechModel("Heinz Erhard", "lustig", new Date(),3232));
        answer.add(new SpeechModel("willi Mill", "nicht lustig", new Date(),32));
        Mockito.when(speechListReader.getSpeakerList(any(List.class))).thenReturn(answer);

        List<URL> urlList = new ArrayList<>();
        URL testUrl = new URL("http://localhost:8080");
        urlList.add(testUrl);
        // act
        QueryResult result = classUnderTest.getStatistics(urlList);

        // assert
        assertEquals(null ,result.getMostSecurity());
    }

    @Test
    public void testPositiveLeastWordy() throws IOException, ParseException {

        // arrange two speaker with same number of speeches
        List<SpeechModel> answer = new ArrayList<>();
        answer.add(new SpeechModel("Heinz Erhard", "lustig", new Date(),3232));
        answer.add(new SpeechModel("Heinz Erhard", "ganz lustig", new Date(),323232));
        answer.add(new SpeechModel("willi Mill", "nicht lustig", new Date(),32));
        Mockito.when(speechListReader.getSpeakerList(any(List.class))).thenReturn(answer);

        List<URL> urlList = new ArrayList<>();
        URL testUrl = new URL("http://localhost:8080");
        urlList.add(testUrl);
        // act
        QueryResult result = classUnderTest.getStatistics(urlList);

        // assert
        assertEquals("willi Mill" ,result.getLeastWordy());
    }

    @Test
    public void testTwoSpeakerHaveTheLeastWordy() throws IOException, ParseException {

        // arrange two speaker with same number of words
        List<SpeechModel> answer = new ArrayList<>();
        answer.add(new SpeechModel("Heinz Erhard", "lustig", new Date(),16));
        answer.add(new SpeechModel("Heinz Erhard", "lustig", new Date(),16));
        answer.add(new SpeechModel("willi Mill", "nicht lustig", new Date(),32));
        Mockito.when(speechListReader.getSpeakerList(any(List.class))).thenReturn(answer);

        List<URL> urlList = new ArrayList<>();
        URL testUrl = new URL("http://localhost:8080");
        urlList.add(testUrl);
        // act
        QueryResult result = classUnderTest.getStatistics(urlList);

        // assert
        assertEquals(null ,result.getLeastWordy());
    }

    @Test
    public void testListIsOnlyOneElementForLeastWordy() throws IOException, ParseException {

        // arrange two speaker with same number of words
        List<SpeechModel> answer = new ArrayList<>();
        answer.add(new SpeechModel("Heinz Erhard", "lustig", new Date(),16));
        Mockito.when(speechListReader.getSpeakerList(any(List.class))).thenReturn(answer);

        List<URL> urlList = new ArrayList<>();
        URL testUrl = new URL("http://localhost:8080");
        urlList.add(testUrl);
        // act
        QueryResult result = classUnderTest.getStatistics(urlList);

        // assert
        assertEquals("Heinz Erhard" ,result.getLeastWordy());
    }

    @Test
    public void testListOfTheLeastWordyIsEmpty() throws IOException, ParseException {
        List<SpeechModel> answer = new ArrayList<>();
        Mockito.when(speechListReader.getSpeakerList(any(List.class))).thenReturn(answer);

        List<URL> urlList = new ArrayList<>();
        URL testUrl = new URL("http://localhost:8080");
        urlList.add(testUrl);
        // act
        QueryResult result = classUnderTest.getStatistics(urlList);

        // assert
        assertEquals(null ,result.getLeastWordy());
    }
}