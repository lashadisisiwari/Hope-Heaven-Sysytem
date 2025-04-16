package lk.ijse.hopeheavenbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDTO {
    private String RequestId;
    private String UserId;
    private String RequestDate;
    private String RequestTime;
    private String RequestStatus;
    private String RequestLocation;
    private String RequestDescription;
    private String bloodType;

}
