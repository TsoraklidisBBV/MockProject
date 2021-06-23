package application.service;

import application.model.MockModel;
import application.util.CsvReader;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MockServiceImpl implements MockService {

    public String getMockLogic(String url){
        JsonObject json = new JsonObject();

        json.addProperty("mostSpeeches", findSpeaker(CsvReader.csvReader()));
        json.addProperty("mostSecurity", findSecurity(CsvReader.csvReader()));
        json.addProperty("leastWordy", "null");

        return json.toString();
    }

   public String findSpeaker(List<MockModel> modelList){
       List<String> names = new ArrayList<>();

       for (MockModel mockModel : modelList) {
           names.add(mockModel.getName());
       }

       return mostCommon(names);
    }

   public String findSecurity(List<MockModel> modelList){
      String mostSecurity = null;

       for (MockModel mockModel : modelList) {
           if (mockModel.getTitle().equals("Innere Sicherheit")) {
               mostSecurity = mockModel.getName();
           }
       }

       return mostSecurity;
   }


   public static <T> T mostCommon(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        return max.getKey();
   }
}