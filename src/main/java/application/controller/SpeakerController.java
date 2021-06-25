package application.controller;

import application.model.QueryResult;

import application.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.text.ParseException;

@RestController
public class SpeakerController {

    @Autowired
    private final SpeakerService speakerService;

    private SpeakerController(SpeakerService mockApplicationImp) {
        super();
        this.speakerService = mockApplicationImp;
    }

    // TODO: ACCEPT MULTIPLE URL PARA AT ONCE, write exceptions and tests
    @RequestMapping(method = RequestMethod.GET, value = "/evaluation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QueryResult> getMockRest(@RequestParam URL url) throws ParseException {
        QueryResult statistics = speakerService.getStatistics(url);

        return ResponseEntity.ok().body(statistics);
    }
}
