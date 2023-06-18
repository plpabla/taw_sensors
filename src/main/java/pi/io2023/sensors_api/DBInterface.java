package pi.io2023.sensors_api;

public interface DBInterface {
    String insert(DTO obj);
    String delete(DTO obj);
}
