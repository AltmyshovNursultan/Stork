package delivery.stork.repository;

import delivery.stork.model.entity.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelerRepo extends JpaRepository<Traveler, Long> {
}
