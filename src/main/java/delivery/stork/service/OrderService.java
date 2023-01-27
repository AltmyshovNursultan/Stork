package delivery.stork.service;

import delivery.stork.model.dto.OrderDto;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.PassPackageRequest;

public interface OrderService {

    OrderDto passPackage(PassPackageRequest passPackageRequest, User sender);

    OrderDto deliveredPackage(Long orderId,Long status, User traveler);
}
