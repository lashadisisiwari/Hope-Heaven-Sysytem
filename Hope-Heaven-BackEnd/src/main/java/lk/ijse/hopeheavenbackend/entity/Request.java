package lk.ijse.hopeheavenbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "request")
public class Request {
    @Id
    private String RequestId;
    private String UserId;
    private String RequestDate;
    private String RequestTime;
    private String RequestStatus;
    private String RequestLocation;
    private String RequestDescription;
}
