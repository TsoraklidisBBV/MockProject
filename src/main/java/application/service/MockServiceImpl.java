package application.service;

import application.util.CsvReader;
import org.springframework.stereotype.Service;

@Service
public class MockServiceImpl implements MockService {

    public String getMockLogic(String url){
        //CsvReader.csvReader();
        System.out.println(CsvReader.csvReader().get(0).getName());
        return url;
    }
}