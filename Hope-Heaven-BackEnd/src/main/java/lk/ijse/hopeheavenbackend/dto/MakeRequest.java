package lk.ijse.hopeheavenbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MakeRequest {
    private String userId;
    private String fullName;
    private String nicNumber;
    private String email;
    private String contactNumber;
    private String address;
    private String bloodType;
    private String requestDetails;
    private String document;

}
