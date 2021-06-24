package application.controller;

import application.model.QueryResult;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import application.service.SpeakerServiceImpl;

import java.net.URL;

@RestController
@AllArgsConstructor
public class SpeakerController {

    private final SpeakerServiceImpl mockApplicationImp;
    //TODO: ACCEPT MULTIPLE URL PARA AT ONCE, write exceptions and tests
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/evaluation" ,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
        public ResponseEntity<QueryResult> getMockRest(@RequestParam URL url) {
         return ResponseEntity.ok().body(mockApplicationImp.getMockLogic(url));
      }
}

