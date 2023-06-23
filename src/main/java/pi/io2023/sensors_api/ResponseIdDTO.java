package pi.io2023.sensors_api;

import lombok.Data;

@Data
public class ResponseIdDTO {
    String id;

    public ResponseIdDTO(String id) {
        this.id = id;
    }
}
