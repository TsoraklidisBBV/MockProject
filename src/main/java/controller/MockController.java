package controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.MockApplicationImpl;

import java.awt.*;


@RestController
public class MockController {

    private MockApplicationImpl mockApplicationImp;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/evaluation" ,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getMockRest( //change this
                               @RequestParam String url) {

        return mockApplicationImp.getMockLogic(url);
    }
}

