package delivery.stork.service;

import delivery.stork.model.dto.PackageDto;
import delivery.stork.model.entity.Package;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.EditPackage;
import delivery.stork.model.wrapper.PackageRequest;

import java.util.List;

public interface PackageService {
    PackageDto postPackage(PackageRequest packageRequest, User senderPackage);

    void deletePostedPackage(Long id, User senderPackage);

    PackageDto editPackage(PackageRequest editPackage, User senderPackage);

    List<PackageDto> getAllPackages();
}
