package pi.io2023.sensors_api;

import java.util.List;

public interface DBInterface {
    String insert(DTO obj);
    public List<DTO> getTable(String tableName);
    void delete(String tableName, String id);

    DTO getGivenId(String tableName, String id);
}
