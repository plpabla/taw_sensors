package pi.io2023.sensors_api;

abstract class DTO {
    static Integer last_id = 0;


    public abstract String getTableName();

    public void resetCounter()
    {
        last_id = 0;
    }
}
