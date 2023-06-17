package pi.io2023.sensors_api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorDTOTest {
    private SensorDTO instance;
    @BeforeEach
    void setUp() {
        instance = new SensorDTO();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTableNameShouldReturnGivenName() {
        final String expected = "Sensor";

        assertEquals(expected, instance.getTableName());
    }
}