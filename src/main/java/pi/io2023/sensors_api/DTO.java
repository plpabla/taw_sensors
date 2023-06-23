package pi.io2023.sensors_api;

import lombok.Data;

@Data
public abstract class DTO {
    String id;
    abstract public String getTableName();

    public void setId(String id)
    {
        this.id = id;
    }
}
