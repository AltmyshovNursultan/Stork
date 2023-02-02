package delivery.stork.mapper;

import delivery.stork.model.dto.OrderDto;
import delivery.stork.model.entity.Order;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.PassPackageRequest;
import delivery.stork.repository.PackageRepo;
import delivery.stork.repository.StatusRepo;
import delivery.stork.repository.TravelerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final PackageRepo packageRepo;
    private final TravelerRepo travelerRepo;
    private final PackageMapper packageMapper;
    private final TravelerMapper travelerMapper;
    private final UserMapper userMapper;
    private final StatusRepo statusRepo;
    private final StatusMapper statusMapper;
    public Order toOrderFromPass(PassPackageRequest passPackageRequest, User sender) {
        return Order.builder()
                .packages(packageRepo.findById(passPackageRequest.getPackageId()).orElseThrow())
                .traveling(travelerRepo.findById(passPackageRequest.getTravelerId()).orElseThrow())
                .status(statusRepo.findById(1L).orElseThrow())
                .sender(sender)
                .build();
    }

    public OrderDto toDto(Order savedOrder) {
        return OrderDto.builder()
                .id(savedOrder.getId())
                .aPackageDto(packageMapper.toPackageDto(savedOrder.getPackages()))
                .travelingDto(travelerMapper.toTravelerDto(savedOrder.getTraveling()))
                .statusDto(statusMapper.toDto(savedOrder.getStatus()))
                .senderDto(userMapper.toUserDto(savedOrder.getSender()))
                .build();
    }

    public Order toOrderFromUpdated(Order order, Long status) {
        order.setStatus(statusRepo.findById(status).orElseThrow());
        return order;
    }
}
