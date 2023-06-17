package pi.io2023.sensors_api;

import lombok.Data;

@Data
public class SensorDTO extends DTO {
    Integer id;
    String type;
    String unit;
    String description;

    public SensorDTO(String type, String unit, String description) {
        this.id = last_id;
        last_id++;
        this.type = type;
        this.unit = unit;
        this.description = description;
    }

    public SensorDTO() {
        this("n/a","n/a","");
    }

    @Override
    public String getTableName() {
        return "Sensor";
    }
}
