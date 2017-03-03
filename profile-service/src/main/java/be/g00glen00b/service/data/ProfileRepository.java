package be.g00glen00b.service.data;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends JpaRepository<Profile, Long>, JpaSpecificationExecutor<Profile> {
    @Query("SELECT p FROM Profile p WHERE p.id = :id")
    Optional<Profile> findOneOptional(@Param("id") Long id);

    @Query("SELECT p FROM Profile p LEFT JOIN FETCH p.avatar WHERE p.id = :id")
    Optional<Profile> findOneDetailedOptional(@Param("id") Long id);
}
