package delivery.stork.service;

import delivery.stork.model.dto.TravelerDto;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.ServiceRequest;
import delivery.stork.model.wrapper.TravelingEditRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TravelerService {
    TravelerDto postService(ServiceRequest serviceRequest, User userTraveler);

    void deleteTraveling(Long id, User userTraveler);

    ResponseEntity<List<TravelerDto>> getTravelingServices();

    TravelerDto updateTraveling(TravelingEditRequest traveleingEditRequest, User traveler);
}
