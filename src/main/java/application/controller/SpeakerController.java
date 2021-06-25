package application.controller;

import application.model.QueryResult;

import application.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

@RestController
public class SpeakerController {

    @Autowired
    private final SpeakerService speakerService;

    private SpeakerController(SpeakerService mockApplicationImp) {
        super();
        this.speakerService = mockApplicationImp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/evaluation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QueryResult> getMockRest(@RequestParam List<URL> url) throws IOException, ParseException {
        QueryResult statistics = speakerService.getStatistics(url);

        if(statistics == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok().body(statistics);
    }
}
