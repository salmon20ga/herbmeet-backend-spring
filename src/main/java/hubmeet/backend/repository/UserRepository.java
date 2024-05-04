package hubmeet.backend.repository;

import hubmeet.backend.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByUserID(String userID);

    Users findByEmailAndPassword(String email, String password);
}
