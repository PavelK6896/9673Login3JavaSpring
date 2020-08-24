package app.web.pavelk.login5.repository;

import app.web.pavelk.login5.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);//ради опционал
}
