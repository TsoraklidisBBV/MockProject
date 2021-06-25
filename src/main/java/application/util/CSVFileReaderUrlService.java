package application.util;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


@Service
public class CSVFileReaderUrlService {
	public BufferedReader getBufferedReaderFromUrl (URL url) throws IOException {
	    return new BufferedReader(new InputStreamReader(url.openStream()));	}
}
