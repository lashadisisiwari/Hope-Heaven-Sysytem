package lk.ijse.hopeheavenbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "donation")
public class Donation {
    @Id
    private String donationId;
    private String userId;
    private String donationDate;
    private String donationTime;
    private String donationDescription;
    private String bloodType;
}
