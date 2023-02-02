package delivery.stork.repository;

import delivery.stork.model.entity.Traveling;
import delivery.stork.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TravelerRepo extends JpaRepository<Traveling, Long> {

    Optional<Traveling> findTravelerByUserTraveler(User userTraveler);
    @Query(value = "SELECT e FROM Traveling e WHERE e.getFrom like :fromCity% and e.getTo like :toCity% ")
    List<Traveling> findPackagesByFilter(@Param("fromCity") String fromCity, @Param("toCity") String toCity);
}
