package lk.ijse.hopeheavenbackend.repository;

import lk.ijse.hopeheavenbackend.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepo extends JpaRepository<Donation,String> {
}
