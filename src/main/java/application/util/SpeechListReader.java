package application.util;

import application.model.SpeechModel;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeechListReader {

    @Autowired
    private CSVFileReaderUrlService csvFileReaderUrlService;

    public List<SpeechModel> getSpeakerList(List<URL> urlList)
    {
        List<SpeechModel> speakerModelList = new ArrayList<SpeechModel>();
        try {
            for (URL url:urlList) {
                BufferedReader br = csvFileReaderUrlService.getBufferedReaderFromUrl(url);
                if (br == null) {
                    return speakerModelList;
                }

                CSVReader csvReader = new CSVReader(br);
                String[] nextRecord;
                while ((nextRecord = csvReader.readNext()) != null) {
                    SpeechModel populateModel = populateModel(
                            nextRecord[0], nextRecord[1], new SimpleDateFormat("yyyy-MM-dd").parse(nextRecord[2]), Integer.parseInt(nextRecord[3])
                    );
                    speakerModelList.add(populateModel);
                }
            }
         }
        catch (IOException | ParseException | CsvValidationException e)
        {
            e.printStackTrace();
        }

        return speakerModelList;
    }


    public SpeechModel populateModel(String speaker, String title, Date date, int words) {
        SpeechModel speakerModel = new SpeechModel(speaker,title,date,words);
        return speakerModel;
    }
}

