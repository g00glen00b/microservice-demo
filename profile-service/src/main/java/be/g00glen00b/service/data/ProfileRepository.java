package be.g00glen00b.service.data;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends JpaRepository<Profile, String>, JpaSpecificationExecutor<Profile> {
    @Query("SELECT p FROM Profile p WHERE p.username = :username")
    Optional<Profile> findOneOptional(@Param("username") String username);

    @Query("SELECT p FROM Profile p WHERE p.email = :email")
    Optional<Profile> findByEmailOptional(@Param("email") String email);

    @Query("SELECT p FROM Profile p LEFT JOIN FETCH p.avatar WHERE p.username = :username")
    Optional<Profile> findOneDetailedOptional(@Param("username") String username);
}
