package application.util;

import application.model.SpeakerModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvReader {

    public static List<SpeakerModel> csvReader(URL url)
        {
            String line = "";
            String splitBy = ",";
            List<SpeakerModel> speakerModelList = new ArrayList<SpeakerModel>();
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                while ((line = br.readLine()) != null)
                {
                    String[] speakers = line.split(splitBy);
                    speakerModelList.add(populateModel(
                            speakers[0],speakers[1], new SimpleDateFormat("dd-MM-yyyy").parse(speakers[2]), Integer.parseInt(speakers[3])
                    ));
                }
            }
            catch (IOException | ParseException e)
            {
                e.printStackTrace();
            }

            return speakerModelList;
        }


    public static SpeakerModel populateModel(String name, String title, Date date, int words) {
        SpeakerModel speakerModel = new SpeakerModel();

        speakerModel.setName(name);
        speakerModel.setTitle(title);
        speakerModel.setDate(date);
        speakerModel.setWords(words);

        return speakerModel;
    }
}

