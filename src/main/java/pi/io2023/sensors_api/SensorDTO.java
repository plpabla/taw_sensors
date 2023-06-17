package pi.io2023.sensors_api;

public class SensorDTO implements DTO {

    @Override
    public String getTableName() {
        return "Sensor";
    }
}
