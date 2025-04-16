package lk.ijse.hopeheavenbackend.repository;

import lk.ijse.hopeheavenbackend.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<Request,String> {
}
