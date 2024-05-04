package hubmeet.backend.repository;

import hubmeet.backend.entity.User2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository2 extends JpaRepository<User2, Integer> {
}
