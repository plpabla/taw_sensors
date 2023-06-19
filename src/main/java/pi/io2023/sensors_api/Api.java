package pi.io2023.sensors_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
