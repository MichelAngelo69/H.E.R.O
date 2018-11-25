package pl.mb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mb.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
Role findByRole(String role);
}
