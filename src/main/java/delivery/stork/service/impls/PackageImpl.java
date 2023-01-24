package delivery.stork.service.impls;

import delivery.stork.exception.NotAllowedException;
import delivery.stork.exception.NotFoundException;
import delivery.stork.mapper.PackageMapper;
import delivery.stork.model.dto.PackageDto;
import delivery.stork.model.entity.Package;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.PackageEditRequest;
import delivery.stork.model.wrapper.PackageRequest;
import delivery.stork.repository.PackageRepo;
import delivery.stork.repository.UserRepo;
import delivery.stork.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PackageImpl implements PackageService {
    private final UserRepo userRepo;
    private final PackageRepo packageRepo;
    private final PackageMapper packageMapper;
    @Override
    public PackageDto postPackage(PackageRequest packageRequest, User senderPackage) {
        Package newPackage = packageMapper.toPackage(packageRequest,senderPackage);
        packageRepo.save(newPackage);
        return packageMapper.toPackageDto(newPackage);
    }

    @Override
    public void deletePostedPackage(Long id, User senderPackage) {
        Package newPackage = packageRepo.findById(id).orElseThrow(()->
                new NotFoundException("No package found with ID "+ id));
        if (!newPackage.getSenderPackage().equals(senderPackage))
            throw new NotAllowedException("You are not allowed to delete this package");
        packageRepo.delete(newPackage);
    }

    @Override
    public PackageDto editPackage(PackageEditRequest editPackage, User senderPackage) {
        Package existsPackage = packageRepo.findPackageBySenderPackage(senderPackage).orElseThrow(()->
                new NotFoundException("No such a package exist of this sender " + senderPackage.getFullName()));
        Package updatePackage = packageMapper.toPackageFromEdit(editPackage, existsPackage);
        packageRepo.save(updatePackage);
        return packageMapper.toPackageDto(updatePackage);
    }

    @Override
    public List<PackageDto> getAllPackages() {
        List<Package> packages =  packageRepo.findAll();
        return packages.stream().map(packageMapper::toPackageDto).collect(Collectors.toList());
    }

}
