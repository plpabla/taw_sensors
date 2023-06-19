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
    void cannotAddTwoSameItems()
    {
        MeasurementDTO meas = new MeasurementDTO(10,0,20.0);
        instance.insert(meas);

        Throwable exception = assertThrows(IllegalArgumentException.class, ()->instance.insert(meas));

        assertEquals("Given object already exists", exception.getMessage());
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

    @Test
    void removeFromNonExistentTableThrowsException()
    {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->instance.delete("non-existent", "0"));

        assertEquals("Invalid table name", exception.getMessage());
    }

    @Test
    void removeNonExistentIdThrowsException()
    {
        Throwable exception = assertThrows(IndexOutOfBoundsException.class,
                ()->instance.delete("measurement", "42"));

        assertTrue(exception.getMessage().contains("out of bounds"));
    }

    @Test
    void canRemoveElementFromGivenTable()
    {
        SensorDTO sensor = new SensorDTO("temperature","degC","room");
        String id = instance.insert(sensor);

        instance.delete(sensor.getTableName(), id);

        assertFalse(instance.getTable(sensor.getTableName()).contains(sensor));
    }
}