package delivery.stork.repository;

import delivery.stork.model.entity.Traveler;
import delivery.stork.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelerRepo extends JpaRepository<Traveler, Long> {

    Optional<Traveler> findTravelerByUserTraveler(User userTraveler);
}
