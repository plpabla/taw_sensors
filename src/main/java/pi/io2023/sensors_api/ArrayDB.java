package pi.io2023.sensors_api;

import java.util.ArrayList;
import java.util.List;

public class ArrayDB implements DBInterface{
    private List<DTO> sensors = new ArrayList<>();
    private List<DTO> measurements = new ArrayList<>();

    private List<DTO> getCorrespondingList(String name)
    {
        switch(name)
        {
            case "sensor": return sensors;
            case "measurement": return measurements;
            default: throw new IllegalArgumentException("Invalid table name");
        }
    }
    @Override
    public String insert(DTO obj) {
        String tableName = obj.getTableName();
        List<DTO> lst = getCorrespondingList(tableName);
        lst.add(obj);
        int idx = lst.indexOf(obj);
        return String.valueOf(idx);
    }

    @Override
    public String delete(DTO obj) {
        return null;
    }

    public DTO getGivenId(String tableName, String id)
    {
        List<DTO> lst = getCorrespondingList(tableName);
        DTO obj = lst.get(Integer.parseInt(id));
        return obj;
    }
}
