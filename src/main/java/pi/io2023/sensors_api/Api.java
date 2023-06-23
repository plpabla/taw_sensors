package pi.io2023.sensors_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api")
public class Api {
    @Autowired
    private ArrayDB database;

    @GetMapping("/ping")
    public String ping()
    {
        return "pong.";
    }

    @GetMapping("/cleanup")
    public void cleanup()
    {
        database.cleanup();
    }

    @PostMapping(value =  "/sensor",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseIdDTO registerSensor(@RequestBody SensorDTO sensor)
    {
        String id = database.insert(sensor);
        sensor.setId(id);
        ResponseIdDTO resp = new ResponseIdDTO(id);
        return resp;
    }

    @GetMapping(value = "/sensor",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public List<DTO> getAllSensors()
    {
        List<DTO> lst = database.getTable("sensor");

        return lst;
    }

    @GetMapping(value = "/measurement",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public List<DTO> getAllMeasurements()
    {
        List<DTO> lst = database.getTable("measurement");

        return lst;
    }

    @PostMapping(value =  "/measurement",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseIdDTO> addMeasurement(@RequestBody MeasurementDTO meas)
    {
        if(meas.getTimestamp() == 0)
        {
            long timestampMs = Instant.now().toEpochMilli();
            meas.setTimestamp((int)(timestampMs/1000));
        }

        List<DTO> sensors = database.getTable("sensor");
        for(DTO dto : sensors)
        {
            SensorDTO sensor = (SensorDTO) dto;
            if (sensor.getId().equals(meas.getSensorId()))
            {
                String id = database.insert(meas);
                meas.setId(id);
                return ResponseEntity.ok(new ResponseIdDTO(id));
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value="/measurement/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MeasWithDetailsDTO> displayMeasurement(@PathVariable(value="id") String id)
    {
        MeasurementDTO m = null;
        SensorDTO s = null;
        try {
            DTO dto;
            dto = database.getGivenId("measurement", id);
            m = (MeasurementDTO) dto;
            dto = database.getGivenId("sensor",m.getSensorId());
            s = (SensorDTO) dto;
        } catch (IndexOutOfBoundsException e)
        {
            return ResponseEntity.badRequest().build();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Integer timestamp = m.getTimestamp();
        Instant instant = Instant.ofEpochSecond(timestamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        String formattedTimestamp = formatter.format(dateTime);

        MeasWithDetailsDTO meas = new MeasWithDetailsDTO(
                formattedTimestamp,
                m.getValue(),
                s.getUnit(),
                s.getDescription());
        meas.setId(id);
        return ResponseEntity.ok(meas);
    }
}
