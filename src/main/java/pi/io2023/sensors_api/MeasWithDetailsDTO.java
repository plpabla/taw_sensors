package pi.io2023.sensors_api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class MeasWithDetailsDTO extends DTO{
    String description;
    String timestamp;
    Double value;
    String unit;

    public MeasWithDetailsDTO(String timestamp, Double value, String unit, String description) {
        this.timestamp = timestamp;
        this.value = value;
        this.unit = unit;
        this.description = description;
    }

    public MeasWithDetailsDTO()
    {
        this("0",0.0, "","");
    }

    @Override
    @JsonIgnore
    public String getTableName() {
        return "";
    }
}
