package delivery.stork.repository;

import delivery.stork.model.entity.Package;
import delivery.stork.model.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PackageRepo extends JpaRepository<Package, Long> {

    @Query(value = "SELECT e FROM Package e WHERE e.from like :fromCity% and e.to like :toCity% ")
    List<Package> findPackagesByFilter(@Param("fromCity") String fromCity, @Param("toCity") String toCity);

    Optional<Package> findPackageBySenderPackage(@NonNull User senderPackage);
}
