package pi.io2023.sensors_api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class MeasurementDTO extends DTO{
    String sensorId;
    Integer timestamp;
    Double value;

    public MeasurementDTO(String sensorId, Integer timestamp, Double value) {
        this.sensorId = sensorId;
        this.timestamp = timestamp;
        this.value = value;
    }

    public MeasurementDTO()
    {
        this("0",0,0.0);
    }

    @Override
    @JsonIgnore
    public String getTableName() {
        return "measurement";
    }
}
