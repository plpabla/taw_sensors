package pi.io2023.sensors_api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class SensorDTO implements DTO {
    String id;
    String type;
    String unit;
    String description;

    public SensorDTO(String type, String unit, String description) {
        this.type = type;
        this.unit = unit;
        this.description = description;
        this.id = "0";
    }

    public SensorDTO() {
        this("","","");
    }

    @Override
    @JsonIgnore
    public String getTableName() {
        return "sensor";
    }

}
