package pi.io2023.sensors_api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDBTest {
    private ArrayDB instance;
    @BeforeEach
    void setUp() {
        instance = new ArrayDB();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void canAddMeasurementDTOandGetId()
    {
        MeasurementDTO meas = new MeasurementDTO(10,0,20.0);

        String id = instance.insert(meas);

        assertEquals("0", id);
    }

    @Test
    void canAddTwoMeasurementsDTOandGetNextId()
    {
        MeasurementDTO meas = new MeasurementDTO(10,0,20.0);
        instance.insert(meas);
        MeasurementDTO meas2 = new MeasurementDTO(100,0,20.0);

        String id = instance.insert(meas2);

        assertEquals("1", id);
    }

    @Test
    void canAddSensorDTOandGetId()
    {
        SensorDTO sensor = new SensorDTO("temperature","degC","room");

        String id = instance.insert(sensor);

        assertEquals("0", id);
    }

    @Test
    void canAddSensorAndMeasurementAndNotMixId()
    {
        SensorDTO s1 = new SensorDTO("temp","x","x");
        SensorDTO s2 = new SensorDTO("temp","x","y");
        MeasurementDTO m1 = new MeasurementDTO(1,1,1.0);

        instance.insert(s1);
        instance.insert(m1);
        String id = instance.insert(s2);

        assertEquals("1", id);
    }

    @Test
    void canAddSensorAndGetItBack()
    {
        SensorDTO sensor = new SensorDTO("temperature","degC","room");
        String id = instance.insert(sensor);

        SensorDTO retrieved = (SensorDTO) instance.getGivenId(sensor.getTableName(),id);

        assertEquals("room",retrieved.getDescription());
    }
}