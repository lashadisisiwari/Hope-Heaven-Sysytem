package lk.ijse.hopeheavenbackend.repository;


import lk.ijse.hopeheavenbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
    User findByEmail(String email);
}
