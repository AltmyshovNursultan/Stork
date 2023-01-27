package delivery.stork.repository;

import delivery.stork.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo  extends JpaRepository<Status, Long> {
}
