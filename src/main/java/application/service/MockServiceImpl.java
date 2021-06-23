package application.service;

import application.model.MockModel;
import application.util.CsvReader;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MockServiceImpl implements MockService {

    //TODO:NEED TO STORE EACH SESSION
    public String getMockLogic(URL url){
        JsonObject json = new JsonObject();
        List<MockModel> modelList = CsvReader.csvReader(url);
        json.addProperty("mostSpeeches", findSpeaker(modelList)) ;
        json.addProperty("mostSecurity", findSecurity(modelList));
        json.addProperty("leastWordy",  findWords(modelList));
        return json.toString();
    }

   public String findSpeaker(List<MockModel> modelList){
       List<String> names = new ArrayList<>();
       for (MockModel mockModel : modelList) names.add(mockModel.getName());
       return mostCommon(names);
    }

   public String findSecurity(List<MockModel> modelList){
      String mostSecurity = null;
      for (MockModel mockModel : modelList)
           mostSecurity = (mockModel.getTitle().equals("Innere Sicherheit")) ? "null" : mockModel.getName();
      return mostSecurity;
   }

   public String findWords(List<MockModel> modelList){
       Map<String, Integer> map = new HashMap<>();

       for(MockModel mockModel: modelList)
       {
           map.put(mockModel.getName(),mockModel.getWords());
           System.out.println("my name is" + map);
       }

       return "red";
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