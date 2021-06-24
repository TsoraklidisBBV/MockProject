package application.service;

import application.model.QueryResult;
import application.model.SpeakerModel;
import application.util.CsvReader;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    public QueryResult getMockLogic(URL url){
        List<SpeakerModel> modelList = CsvReader.csvReader(url); //TODO: use library for cvsreader
        return queryResultResult(findSpeaker(modelList),findSecurity(modelList),findWords(modelList));
    }

   private String findSpeaker(List<SpeakerModel> modelList){
       List<String> names = new ArrayList<>();
       for (SpeakerModel speakerModel : modelList){
           names.add(speakerModel.getName());
       }
       return mostCommon(names);
    }

   private String findSecurity(List<SpeakerModel> modelList){
      String mostSecurity = null;
      for (SpeakerModel speakerModel : modelList) {
          mostSecurity = (speakerModel.getTitle().equals("Innere Sicherheit")) ? "null" : speakerModel.getName();
      }
      return mostSecurity;
   }

   private String findWords(List<SpeakerModel> modelList){
       if(modelList.isEmpty()){
           return null;
       }

       Map<String, Integer> map = new HashMap<>();

       //TODO: try to do it with collectors, try to find the commonalities between findWords and mostCommon
       for(SpeakerModel speakerModel : modelList)
       {
           String name = speakerModel.getName();
           Integer words = speakerModel.getWords();

           if(!map.containsKey(name)) {
               map.put(name,words);
           }else{
               Integer existingWords = map.get(name);
               map.put(name,words+existingWords);
           }
       }

       Optional<String> resultName =
               map.entrySet().stream().sorted(
                       (entrySet1, entrySet2) -> entrySet1.getValue().compareTo(entrySet2.getValue())
               ).map(entrySet -> entrySet.getKey()).findFirst();

       return resultName.get();
   }

   //TODO: make T generic to  MockModel
   private static <T> T mostCommon(List<T> list) {
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

    private static QueryResult queryResultResult (String mostSpeeches, String mostSecurity, String speakerWithLeastWords) {
        QueryResult queryResult = new QueryResult();

        queryResult.setMostSpeeches(mostSpeeches);
        queryResult.setMostSecurity(mostSecurity);
        queryResult.setLeastWordy(speakerWithLeastWords);

        return queryResult;
    }
}