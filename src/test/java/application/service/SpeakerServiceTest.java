package application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.net.MalformedURLException;
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
    public void testPositive() throws MalformedURLException, ParseException {

        // arrange
        List<SpeechModel> answer = new ArrayList<>();
        answer.add(new SpeechModel("Heinz Erhard", "lustig", new Date(),3232));
        answer.add(new SpeechModel("Heinz Erhard", "ganz lustig", new Date(),323232));
        answer.add(new SpeechModel("willi Mill", "nicht lustig", new Date(),32));
        Mockito.when(speechListReader.getSpeakerList(any(URL.class))).thenReturn(answer);

        URL testUrl = new URL("http://localhost:8080");
        // act
        QueryResult result = classUnderTest.getStatistics(testUrl);

        // assert
        assertEquals("Heinz Erhard",result.getMostSpeeches());
    }

    @Test
    public void testNegativeMostSpeeches() throws MalformedURLException, ParseException {

        // arrange two speaker with same number of speeches
        List<SpeechModel> answer = new ArrayList<>();
        answer.add(new SpeechModel("Heinz Erhard", "lustig", new Date(),3232));
        answer.add(new SpeechModel("willi Mill", "nicht lustig", new Date(),32));
        Mockito.when(speechListReader.getSpeakerList(any(URL.class))).thenReturn(answer);

        URL testUrl = new URL("http://localhost:8080");
        // act
        QueryResult result = classUnderTest.getStatistics(testUrl);

        // assert
        assertEquals(null ,result.getMostSpeeches());
    }

    @Test
    public void testPositiveLeastWordy() throws MalformedURLException, ParseException {

        // arrange two speaker with same number of speeches
        List<SpeechModel> answer = new ArrayList<>();
        answer.add(new SpeechModel("Heinz Erhard", "lustig", new Date(),3232));
        answer.add(new SpeechModel("Heinz Erhard", "ganz lustig", new Date(),323232));
        answer.add(new SpeechModel("willi Mill", "nicht lustig", new Date(),32));
        Mockito.when(speechListReader.getSpeakerList(any(URL.class))).thenReturn(answer);

        URL testUrl = new URL("http://localhost:8080");
        // act
        QueryResult result = classUnderTest.getStatistics(testUrl);

        // assert
        assertEquals("willi Mill" ,result.getLeastWordy());
    }

    @Test
    public void testTwoSpeakerHaveTheLeastWordy() throws MalformedURLException, ParseException {

        // arrange two speaker with same number of words
        List<SpeechModel> answer = new ArrayList<>();
        answer.add(new SpeechModel("Heinz Erhard", "lustig", new Date(),16));
        answer.add(new SpeechModel("Heinz Erhard", "lustig", new Date(),16));
        answer.add(new SpeechModel("willi Mill", "nicht lustig", new Date(),32));
        Mockito.when(speechListReader.getSpeakerList(any(URL.class))).thenReturn(answer);

        URL testUrl = new URL("http://localhost:8080");
        // act
        QueryResult result = classUnderTest.getStatistics(testUrl);

        // assert
        assertEquals("Heinz Erhard" ,result.getLeastWordy());
    }

    @Test
    public void testListOfTheLeastWordyIsEmpty() throws MalformedURLException, ParseException {
        List<SpeechModel> answer = new ArrayList<>();
        Mockito.when(speechListReader.getSpeakerList(any(URL.class))).thenReturn(answer);

        URL testUrl = new URL("http://localhost:8080");
        // act
        QueryResult result = classUnderTest.getStatistics(testUrl);

        // assert
        assertEquals(null ,result.getLeastWordy());
    }
}