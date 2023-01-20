package delivery.stork.mapper;

import delivery.stork.model.dto.PackageDto;
import delivery.stork.model.entity.Package;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.PackageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PackageMapper {
    private final UserMapper userMapper;
    public Package toPackage(PackageRequest packageRequest, User senderPackage) {
        return Package.builder()
                .nameOfPackage(packageRequest.getNameOfPackage())
                .id(packageRequest.getId())
                .to(packageRequest.getTo())
                .from(packageRequest.getFrom())
                .weight(packageRequest.getWeight())
                .startDate(packageRequest.getStartDate())
                .deadline(packageRequest.getDeadline())
                .senderPackage(senderPackage)
                .build();
    }

    public PackageDto toPackageDto(Package newPackage) {
        return PackageDto.builder()
                .id(newPackage.getId())
                .nameOfPackage(newPackage.getNameOfPackage())
                .wight(newPackage.getWeight())
                .price(newPackage.getPrice())
                .to(newPackage.getTo())
                .from(newPackage.getFrom())
                .startDate(newPackage.getStartDate())
                .deadline(newPackage.getDeadline())
                .userDto(userMapper.toUserDto(newPackage.getSenderPackage()))
                .build();
    }
}
