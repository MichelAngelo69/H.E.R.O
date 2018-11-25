package pl.mb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mb.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
