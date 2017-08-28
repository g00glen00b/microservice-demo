package be.g00glen00b.service.data;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
    Optional<State> findOneByEmail(String email);
    Optional<State> findOneByUsername(String username);
}
