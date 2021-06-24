package application.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@AllArgsConstructor
public class CsvMockController {
    @RequestMapping(
            method = RequestMethod.GET,
            value =  "/report/{dataName}" ,
            produces = "text/csv"
    )
    public ResponseEntity generateReport(@PathVariable(value = "dataName") String reportName) {
        //TODO: classpath resource
        File file = new File("C:\\Users\\tsoraklidis\\IdeaProjects\\MockProject\\src\\main\\resources\\"+reportName+".csv");

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + reportName + ".csv")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new FileSystemResource(file));

    }
}
