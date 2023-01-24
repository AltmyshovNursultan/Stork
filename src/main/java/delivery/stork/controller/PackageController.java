package delivery.stork.controller;

import delivery.stork.model.dto.PackageDto;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.PackageEditRequest;
import delivery.stork.model.wrapper.PackageRequest;
import delivery.stork.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/packages")
public class PackageController {
    private final PackageService packageService;
    @PostMapping("/post")
    ResponseEntity<?> postPackage(@RequestBody PackageRequest packageRequest,
                                  @AuthenticationPrincipal User senderPackage){
        return ResponseEntity.ok(packageService.postPackage(packageRequest,senderPackage));
    }
    @DeleteMapping("/delete")
    ResponseEntity<?> deletePostedPackage(@RequestParam Long id, @AuthenticationPrincipal User senderPackage){
        packageService.deletePostedPackage(id, senderPackage);
        return ResponseEntity.ok("You successfully deleted package");
    }
    @PostMapping("/update")
    ResponseEntity<PackageDto> editPackage(@RequestBody PackageEditRequest editPackage,
                                           @AuthenticationPrincipal User senderPackage){
        return ResponseEntity.ok(packageService.editPackage(editPackage, senderPackage));
    }
    @GetMapping("/get-all")
    ResponseEntity<List<PackageDto>> getAllPackages(){
        return ResponseEntity.ok(packageService.getAllPackages());
    }

}
