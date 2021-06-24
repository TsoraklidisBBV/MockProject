package application.util;

import application.model.SpeakerModel;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpeakerListInitialization {

    public static List<SpeakerModel> getSpeakerList(URL url)
        {
            String[] nextRecord;
            List<SpeakerModel> speakerModelList = new ArrayList<SpeakerModel>();
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                CSVReader csvReader = new CSVReader(br);
                while ((nextRecord = csvReader.readNext()) != null)
                {
                    speakerModelList.add(populateModel(
                            nextRecord[0],nextRecord[1], new SimpleDateFormat("dd-MM-yyyy").parse(nextRecord[2]), Integer.parseInt(nextRecord[3])
                    ));
                }
            }
            catch (IOException | ParseException | CsvValidationException e)
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

