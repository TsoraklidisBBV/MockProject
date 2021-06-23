package application.util;

import application.model.MockModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvReader {

    public static List<MockModel> csvReader(URL url)
        {
            String line = "";
            String splitBy = ",";
            List<MockModel> mockModelList = new ArrayList<MockModel>();
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                while ((line = br.readLine()) != null)
                {
                    String[] speakers = line.split(splitBy);
                    mockModelList.add(populateModel(
                            speakers[0],speakers[1], new SimpleDateFormat("dd-MM-yyyy").parse(speakers[2]), Integer.parseInt(speakers[3])
                    ));
                }
            }
            catch (IOException | ParseException e)
            {
                e.printStackTrace();
            }

            return mockModelList;
        }


    public static MockModel populateModel(String name, String title, Date date, int words) {
        MockModel mockModel = new MockModel();

        mockModel.setName(name);
        mockModel.setTitle(title);
        mockModel.setDate(date);
        mockModel.setWords(words);

        List<MockModel> test = new ArrayList<>();
        test.add(mockModel);

        return  mockModel;
    }
}

