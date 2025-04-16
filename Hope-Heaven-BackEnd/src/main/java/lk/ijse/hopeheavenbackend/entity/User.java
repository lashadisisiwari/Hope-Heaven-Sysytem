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
@Table(name="users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String address;
    private String contact;
    private String nic;
    private String password;
    private String role;
}
