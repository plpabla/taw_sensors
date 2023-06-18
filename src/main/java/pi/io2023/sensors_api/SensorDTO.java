package pi.io2023.sensors_api;

import lombok.Data;

@Data
public class SensorDTO implements DTO {
    String type;
    String unit;
    String description;

    public SensorDTO(String type, String unit, String description) {
        this.type = type;
        this.unit = unit;
        this.description = description;
    }

    public SensorDTO() {
        this("","","");
    }

    @Override
    public String getTableName() {
        return "Sensor";
    }
}
