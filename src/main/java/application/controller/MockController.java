package application.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import application.service.MockServiceImpl;

import java.io.File;
import java.net.URL;

@RestController
@AllArgsConstructor
public class MockController {

    private final MockServiceImpl mockApplicationImp;
    //TODO: ACCEPT MULTIPLE URL PARA AT ONCE, write exceptions and tests
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/evaluation" ,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> getMockRest(@RequestParam URL url) {
         return ResponseEntity.ok().body(mockApplicationImp.getMockLogic(url));
      }

    @RequestMapping(
            method = RequestMethod.GET,
            value =  "/report/{dataName}" ,
            produces = "text/csv"
    )
    public ResponseEntity generateReport(@PathVariable(value = "dataName") String reportName) {

        File file = new File("C:\\Users\\tsoraklidis\\IdeaProjects\\MockProject\\src\\main\\resources\\"+reportName+".csv");

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + reportName + ".csv")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new FileSystemResource(file));

    }
}

