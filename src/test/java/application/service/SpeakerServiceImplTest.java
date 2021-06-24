package application.service;

import application.controller.CsvMockController;
import application.model.QueryResult;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;


class SpeakerServiceImplTest {

    SpeakerServiceImpl speakerServiceImp;

    @Mock
    CsvMockController csvMockController;


    QueryResult mockQueryResult;
    URL url = new URL("http://localhost:8080/report/MockData");

    SpeakerServiceImplTest() throws MalformedURLException {
    }

    @Ignore
    @Test
    public void getRed(){
        mockQueryResult.setLeastWordy("Red");
        mockQueryResult.setMostSecurity("Red");
        mockQueryResult.setMostSpeeches("Red");

        Mockito.when(csvMockController.generateReport("MockData")).thenReturn(ResponseEntity.ok(mockQueryResult));
        final QueryResult speakerServiceResult = speakerServiceImp.getMockLogic(url);
        assertEquals(speakerServiceResult,mockQueryResult);
    }
}