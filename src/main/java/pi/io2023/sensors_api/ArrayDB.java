package pi.io2023.sensors_api;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
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
        if(lst.contains(obj))
        {
            throw new IllegalArgumentException("Given object already exists");
        }

        lst.add(obj);
        int idx = lst.indexOf(obj);
        return String.valueOf(idx);
    }

    @Override
    public void delete(String tableName, String id) {
        List<DTO> lst = getCorrespondingList(tableName);
        lst.remove(Integer.parseInt(id));
    }

    @Override
    public DTO getGivenId(String tableName, String id)
    {
        List<DTO> lst = getCorrespondingList(tableName);
        DTO obj = lst.get(Integer.parseInt(id));
        return obj;
    }

    @Override
    public List<DTO> getTable(String tableName)
    {
        return getCorrespondingList(tableName);
    }
}
