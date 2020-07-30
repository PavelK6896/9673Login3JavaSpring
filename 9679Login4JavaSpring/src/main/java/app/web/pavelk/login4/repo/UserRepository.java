package app.web.pavelk.login4.repo;

import app.web.pavelk.login4.domain.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User1, Long> {
    <T> T findByUsername(String username, Class<T> type);


    @Query(value = "SELECT nextval(pg_get_serial_sequence('users', 'id'))", nativeQuery = true)
    Long getNextId();
}
