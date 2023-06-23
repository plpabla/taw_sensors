package pi.io2023.sensors_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
}
