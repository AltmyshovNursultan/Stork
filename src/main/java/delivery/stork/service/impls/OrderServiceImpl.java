package delivery.stork.service.impls;

import delivery.stork.exception.NotAllowedException;
import delivery.stork.exception.NotFoundException;
import delivery.stork.mapper.OrderMapper;
import delivery.stork.model.dto.OrderDto;
import delivery.stork.model.entity.Order;
import delivery.stork.model.entity.Package;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.PassPackageRequest;
import delivery.stork.repository.OrderRepo;
import delivery.stork.repository.PackageRepo;
import delivery.stork.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final PackageRepo packageRepo;
    @Override
    public OrderDto passPackage(PassPackageRequest passPackageRequest, User sender){
        Package aPackage = packageRepo.findById(passPackageRequest.getPackageId()).orElseThrow(()->
                new NotFoundException("No such a package found, please create new package!"));

        if (!checkOrder(aPackage)) throw new NotAllowedException("You are already passed this package!");
        Order newOrder = orderMapper.toOrderFromPass(passPackageRequest, sender);
        Order savedOrder = orderRepo.save(newOrder);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDto deliveredPackage(Long orderId,Long status, User traveler) {
        Order order = orderRepo.findById(orderId).orElseThrow(()->
                new NotFoundException("No such a order found with id" + orderId));
        if (!order.getTraveling().getId().equals(traveler.getId()))
            throw new NotAllowedException(("You are not to update this order"));
        Order updatedOrder = orderMapper.toOrderFromUpdated(order,status);
        orderRepo.save(updatedOrder);
        return orderMapper.toDto(updatedOrder);
    }

    protected boolean checkOrder(Package packageId){
        Order order = orderRepo.findOrderByPackages(packageId);
        return order == null;
    }
}
