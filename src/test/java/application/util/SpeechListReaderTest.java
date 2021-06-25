package application.util;

import application.model.SpeechModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SpeechListReaderTest {

	@InjectMocks
	SpeechListReader classUnderTest;

	@Mock
	CSVFileReaderUrlService csvFileReaderUrlServiceMock;

	@Test
	public void testPositiveTest() throws IOException {
		BufferedReader brValueMock = new BufferedReader(new FileReader("src/main/resources/MockData.csv"));
		Mockito.when(csvFileReaderUrlServiceMock.getBufferedReaderFromUrl(any(URL.class))).thenReturn(brValueMock);

		URL testUrl = new URL("http://localhost:8080");
		//act
		List<SpeechModel> result = classUnderTest.getSpeakerList(testUrl);

		//assert
		assertEquals("Alexander Abel",result.get(0).getSpeaker());
		assertEquals(4,result.size());
	}

	@Test
	public void testNegativeTest() throws IOException {
		BufferedReader brValueMock = null;
		Mockito.when(csvFileReaderUrlServiceMock.getBufferedReaderFromUrl(any(URL.class))).thenReturn(brValueMock);

		URL testUrl = new URL("http://localhost:8080");
		//act
		List<SpeechModel> result = classUnderTest.getSpeakerList(testUrl);
		assertTrue(result.isEmpty());
	}
}
