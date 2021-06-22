package application.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import application.service.MockServiceImpl;

@RestController
@AllArgsConstructor
public class MockController {

    private final MockServiceImpl mockApplicationImp;

      @RequestMapping(
            method = RequestMethod.GET,
            value = "/evaluation" ,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getMockRest(@RequestParam String url) {
        return mockApplicationImp.getMockLogic(url);
      }
}
