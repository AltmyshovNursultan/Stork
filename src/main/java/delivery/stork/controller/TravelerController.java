package delivery.stork.controller;

import delivery.stork.model.dto.TravelerDto;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.ServiceRequest;
import delivery.stork.service.TravelerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/traveler")
@RequiredArgsConstructor
@RestController
public class TravelerController {
    private final TravelerService travelerService;
    @PostMapping("/post")
    ResponseEntity<TravelerDto> postService(@RequestBody ServiceRequest serviceRequest,
                                            @AuthenticationPrincipal User userTraveler){
        return ResponseEntity.ok(travelerService.postService(serviceRequest,userTraveler));
    }
    @DeleteMapping("/delete")
    ResponseEntity<?> deleteTraveling(@RequestParam Long id, @AuthenticationPrincipal User userTraveler){
        travelerService.deleteTraveling(id, userTraveler);
        return ResponseEntity.ok("You successfully deleted.");
    }
    @GetMapping("/get-all")
    ResponseEntity<List<TravelerDto>> getTravelingServices(){
        return travelerService.getTravelingServices();
    }
}
