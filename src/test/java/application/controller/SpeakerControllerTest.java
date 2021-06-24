package application.controller;

import application.service.SpeakerServiceImpl;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpeakerController.class)
class SpeakerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private final SpeakerServiceImpl mockApplicationImp;

    SpeakerControllerTest(SpeakerServiceImpl mockApplicationImp) {
        this.mockApplicationImp = mockApplicationImp;
    }

    @Ignore
    @Test
    public void testGetMockData() throws Exception {
        URL url = new URL("http://localhost:8080/evaluation?url=http://localhost:8080/report/MockData");

       // Optional<SelectedHardwareData> result = Optional.of(SelectedHardwareData.of(EXTERNAL_ID, null));

        //Mockito.when(mockApplicationImp.getMockLogic(url)).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders.get("/evaluation")).andExpect(status().isOk());
    }

}