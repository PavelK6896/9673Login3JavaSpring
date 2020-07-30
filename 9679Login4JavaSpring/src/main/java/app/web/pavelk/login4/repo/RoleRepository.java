package app.web.pavelk.login4.repo;

import app.web.pavelk.login4.domain.Role1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface RoleRepository extends JpaRepository<Role1, Long> {
    Role1 findByName(String name);
}
