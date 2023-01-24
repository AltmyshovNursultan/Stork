package delivery.stork.repository;

import delivery.stork.model.entity.Package;
import delivery.stork.model.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackageRepo extends JpaRepository<Package, Long> {


    Optional<Package> findPackageBySenderPackage(@NonNull User senderPackage);
}
