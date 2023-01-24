package delivery.stork.service;

import delivery.stork.model.dto.PackageDto;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.PackageEditRequest;
import delivery.stork.model.wrapper.PackageRequest;

import java.util.List;

public interface PackageService {
    PackageDto postPackage(PackageRequest packageRequest, User senderPackage);

    void deletePostedPackage(Long id, User senderPackage);

    PackageDto editPackage(PackageEditRequest editPackage, User senderPackage);

    List<PackageDto> getAllPackages();
}
