package pi.io2023.sensors_api;

import lombok.Data;

@Data
public class MeasurementDTO extends DTO{
    Integer sensorId;
    Integer timestamp;
    Double value;

    public MeasurementDTO(Integer sensorId, Integer timestamp, Double value) {
        this.sensorId = sensorId;
        this.timestamp = timestamp;
        this.value = value;
    }

    public MeasurementDTO()
    {
        this(0,0,0.0);
    }

    @Override
    public String getTableName() {
        return "Measurement";
    }
}
