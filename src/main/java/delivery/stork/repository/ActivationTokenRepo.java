package delivery.stork.repository;

import delivery.stork.model.entity.ActivationToken;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivationTokenRepo extends JpaRepository<ActivationToken, Long> {
    boolean existsActivationTokenByEmail(String email);
    Optional<ActivationToken> findActivationTokenByToken(String token);
}
