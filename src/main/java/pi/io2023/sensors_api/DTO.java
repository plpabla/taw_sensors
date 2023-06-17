package pi.io2023.sensors_api;

abstract class DTO {
    static Integer last_id = 0;
    Integer id;

    public DTO() {
        this.id = last_id;
        last_id++;
    }

    public abstract String getTableName();

    public int getId()
    {
        return id;
    }

    public void resetCounter()
    {
        last_id = 0;
    }
}
