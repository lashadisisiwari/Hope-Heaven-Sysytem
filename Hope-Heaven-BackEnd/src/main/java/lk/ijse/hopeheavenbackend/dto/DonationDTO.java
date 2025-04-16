package lk.ijse.hopeheavenbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DonationDTO {
    private String donationId;
    private String userId;
    private String donationDate;
    private String donationTime;
    private String donationDescription;
    private String bloodType;
}
