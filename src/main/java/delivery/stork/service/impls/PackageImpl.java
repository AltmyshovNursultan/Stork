package delivery.stork.service.impls;

import delivery.stork.exception.InvalidDataException;
import delivery.stork.exception.NotAllowedException;
import delivery.stork.exception.NotFoundException;
import delivery.stork.mapper.PackageMapper;
import delivery.stork.model.dto.PackageDto;
import delivery.stork.model.entity.Package;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.PackageEditRequest;
import delivery.stork.model.wrapper.PackageRequest;
import delivery.stork.model.wrapper.SearchRequest;
import delivery.stork.repository.PackageRepo;
import delivery.stork.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PackageImpl implements PackageService {

    private final PackageRepo packageRepo;
    private final PackageMapper packageMapper;
    @Override
    public PackageDto postPackage(PackageRequest packageRequest, User senderPackage) {
        if(!validateDate(packageRequest.getStartDate(),packageRequest.getDeadline()))
            throw new InvalidDataException("Please enter proper date!");

        Package newPackage = packageMapper.toPackage(packageRequest,senderPackage);
        packageRepo.save(newPackage);
        return packageMapper.toPackageDto(newPackage);
    }

    private boolean validateDate(LocalDateTime startDate, LocalDateTime deadline) {
        return !startDate.isEqual(deadline) && !startDate.isAfter(deadline);
    }


    @Override
    public void deletePostedPackage(Long id, User senderPackage) {
        Package newPackage = packageRepo.findById(id).orElseThrow(()->
                new NotFoundException("No package found with ID "+ id));
        if (!newPackage.getSenderPackage().getId().equals(senderPackage.getId()))
            throw new NotAllowedException("You are not allowed to delete this package");
        packageRepo.delete(newPackage);
    }

    @Override
    public PackageDto editPackage(Long id,PackageEditRequest editPackage, User senderPackage) {
        if(!validateDate(editPackage.getStartDate(),editPackage.getDeadline()))
            throw new InvalidDataException("Please enter proper date!");
        Package existsPackage = packageRepo.findById(id).orElseThrow(()-> new NotFoundException("Package not found"));
        if (!existsPackage.getSenderPackage().getId().equals(senderPackage.getId()))
            throw new NotAllowedException("You are allowed to update this package");

        Package updatePackage = packageMapper.toPackageFromEdit(editPackage, existsPackage);
        packageRepo.save(updatePackage);
        return packageMapper.toPackageDto(updatePackage);
    }

    @Override
    public List<PackageDto> getAllPackages() {
        List<Package> packages =  packageRepo.findAll();
        return packages.stream().map(packageMapper::toPackageDto).collect(Collectors.toList());
    }

    @Override
    public List<PackageDto> searchPackage(SearchRequest searchPackageRequest) {
        List<Package> packages = packageRepo.findPackagesByFilter(searchPackageRequest.getFromCity(), searchPackageRequest.getToCity());
        return packages.stream().map(packageMapper :: toPackageDto).collect(Collectors.toList());
    }

}
