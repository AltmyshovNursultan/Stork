package delivery.stork.controller;

import delivery.stork.model.dto.OrderDto;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.PassPackageRequest;
import delivery.stork.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/pass")
    public ResponseEntity<OrderDto> passPackage(@RequestBody PassPackageRequest passPackageRequest, @AuthenticationPrincipal User sender){
        return ResponseEntity.ok(orderService.passPackage(passPackageRequest, sender));
    }
    @PostMapping("/delivered")
    public ResponseEntity<OrderDto> delivered(@RequestParam Long orderId,@RequestParam Long status, @AuthenticationPrincipal User traveler){
        return ResponseEntity.ok(orderService.deliveredPackage(orderId,status,traveler));
    }
}
