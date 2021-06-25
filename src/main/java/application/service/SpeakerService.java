package application.service;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import application.util.SpeechListReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.model.QueryResult;
import application.model.SpeechModel;

@Service
public class SpeakerService {

    @Autowired
    SpeechListReader speechListReader;

    public QueryResult getStatistics(List<URL> urlList) throws ParseException {
        List<SpeechModel> modelList = speechListReader.getSpeakerList(urlList);
        return queryResult(findSpeakerWithSpeeches(modelList), findSecurity(modelList), findWords(modelList));
    }

    private String findSpeakerWithSpeeches(List<SpeechModel> modelList) throws ParseException {
        if (modelList.isEmpty()) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse ( "2012-12-31" );

        List<String> names = new ArrayList<>();
        for (SpeechModel speakerModel : modelList) {
            if(speakerModel.getDate().after(date)) {
                names.add(speakerModel.getSpeaker());
            }
        }
        return mostCommon(names);
    }

    private String findSecurity(List<SpeechModel> modelList) {
        if (modelList.isEmpty()) {
            return null;
        }

        List<String> speakers = new ArrayList<>();
        for (SpeechModel speakerModel : modelList) {
            if(speakerModel.getTitle().equals("Innere Sicherheit")) {
                speakers.add(speakerModel.getSpeaker());
            }
        }

        if(speakers.isEmpty()){
            return null;
        }

        return mostCommon(speakers);
    }

    private String findWords(List<SpeechModel> modelList) {
        if (modelList.isEmpty()) {
            return null;
        }

        Map<String, Integer> map = new HashMap<>();

        /* Looking for elements that have the same Speaker.
        *  If they are, we add their values.
        */
        for (SpeechModel speakerModel : modelList) {
            String name = speakerModel.getSpeaker();
            Integer words = speakerModel.getWords();

            if (!map.containsKey(name)) {
                map.put(name, words);
            } else {
                Integer existingWords = map.get(name);
                map.put(name, words + existingWords);
            }
        }
     
        /* Looking for elements with the same value. 
         * If the values are different we add them to the Set.
         * If there are values are the same this will create a Set with less elements than the map.
         */
        Set<Integer> set = new HashSet<Integer>();
        for (Integer value : map.values()) {
            set.add(value);
        }

        if(map.size() != set.size() && map.size()!=1){
            return null;
        }

        Optional<String> resultName = map.entrySet().stream()
                .sorted((entrySet1, entrySet2) -> entrySet1.getValue().compareTo(entrySet2.getValue()))
               .map(Map.Entry::getKey).findFirst();

        return resultName.get();
    }

    private <SpeechModel> SpeechModel mostCommon(List<SpeechModel> list) {
        Map<SpeechModel, Integer> map = new HashMap<>();

        for (SpeechModel t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Map.Entry<SpeechModel, Integer> max = null;
        boolean singleMax = false;

        for (Map.Entry<SpeechModel, Integer> nextValue : map.entrySet()) {
            if (max == null || nextValue.getValue() >= max.getValue()) {
                if (max != null) {
                    if (max.getValue() == nextValue.getValue()) {
                        singleMax = false;
                    } else {
                        singleMax = true;
                    }
                } else {
                    singleMax = true;
                }
                max = nextValue;
            }
        }
        if (singleMax != true) {
            return null;
        } else {
            return max.getKey();
        }
    }
    
    private QueryResult queryResult(String mostSpeeches, String mostSecurity, String speakerWithLeastWords) {
        QueryResult queryResult = new QueryResult();

        queryResult.setMostSpeeches(mostSpeeches);
        queryResult.setMostSecurity(mostSecurity);
        queryResult.setLeastWordy(speakerWithLeastWords);

        return queryResult;
    }
}