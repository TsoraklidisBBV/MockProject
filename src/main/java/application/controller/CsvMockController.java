package application.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class CsvMockController {

    @RequestMapping(
            method = RequestMethod.GET,
            value =  "/report/{dataName}" ,
            produces = "text/csv"
    )
    public ResponseEntity generateReport(@PathVariable(value = "dataName") String dataName) {
        File file = new File("src/main/resources/"+dataName+".csv");

        if(file.length() == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + dataName + ".csv")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new FileSystemResource(file));
    }
}
