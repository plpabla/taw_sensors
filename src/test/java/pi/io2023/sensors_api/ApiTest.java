package pi.io2023.sensors_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApiTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void cleanup() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cleanup"));
    }
    @Test
    void pingWorks() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ping"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("pong."));
    }

    @Test
    void canRegisterSensorAndGetId() throws Exception
    {
        String requestBody = "{\"type\":\"temperature\", \"unit\":\"degC\", \"description\":\"living room\"}";
        String expectedResponse = "{\"id\":\"0\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/sensor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }

    @Test
    void canRegisterSecondSensorAndGetNextId() throws Exception
    {
        String requestBody1 = "{\"type\":\"temperature\", \"unit\":\"degC\", \"description\":\"living room\"}";
        String requestBody2 = "{\"type\":\"temperature\", \"unit\":\"degC\", \"description\":\"living room 2\"}";
        String expectedResponse2 = "{\"id\":\"1\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/sensor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody1));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/sensor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse2));
    }

    @Test
    void canRetrieveEmptySensorsList() throws Exception
    {
        String expectedResponse = "[]";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/sensor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }

    @Test
    void canRetrireveRegisteredSensor() throws Exception
    {
        String requestBody = "{\"type\":\"temperature\", \"unit\":\"deg C\", \"description\":\"living room\"}";
        String expectedResponse = "[{\"id\":\"0\",\"type\":\"temperature\",\"unit\":\"deg C\",\"description\":\"living room\"}]";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/sensor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/sensor")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }

    @Test
    void canRetrireveRegisteredTwoSensors() throws Exception
    {
        String requestBody1 = "{\"type\":\"temperature\", \"unit\":\"deg C\", \"description\":\"living room\"}";
        String requestBody2 = "{\"type\":\"temperature\", \"unit\":\"deg C\", \"description\":\"living room 2\"}";
        String expectedResponse = "[{\"id\":\"0\",\"type\":\"temperature\",\"unit\":\"deg C\",\"description\":\"living room\"},{\"id\":\"1\",\"type\":\"temperature\",\"unit\":\"deg C\",\"description\":\"living room 2\"}]";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/sensor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody1));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/sensor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/sensor")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }

    @Test
    void cannotAddMeasurementForNonexistentSensor() throws Exception
    {

    }
}