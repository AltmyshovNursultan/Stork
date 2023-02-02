package delivery.stork.repository;

import delivery.stork.model.entity.Order;
import delivery.stork.model.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
   Order findOrderByPackages(Package existsPackage);
}
