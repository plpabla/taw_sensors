package pi.io2023.sensors_api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeasurementDTOTest {
    private MeasurementDTO instance;
    @BeforeEach
    void setUp() {
        instance = new MeasurementDTO();
    }

    @AfterEach
    void tearDown() {
        instance.resetCounter();
    }

    @Test
    void getTableNameShouldReturnGivenName() {
        final String expected = "Measurement";

        assertEquals(expected, instance.getTableName());
    }

    @Test
    void canInstantiateWithParameters()
    {
        MeasurementDTO obj = new MeasurementDTO(1,100,3.14);

        assertEquals(1, obj.getSensorId());
        assertEquals(100, obj.getTimestamp());
        assertEquals(3.14, obj.getValue(),1e-6);
    }
    @Test
    void createdDTOHasIndex0()
    {
        assertEquals(0, instance.getId());
    }

    @Test
    void createdSecondDTOHasIndex1()
    {
        MeasurementDTO instance1 = new MeasurementDTO();
        assertEquals(1, instance1.getId());
    }
}