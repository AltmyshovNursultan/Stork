package delivery.stork.service.impls;

import delivery.stork.exception.NotAllowedException;
import delivery.stork.exception.NotFoundException;
import delivery.stork.mapper.TravelerMapper;
import delivery.stork.model.dto.TravelerDto;
import delivery.stork.model.entity.Traveler;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.ServiceRequest;
import delivery.stork.model.wrapper.TravelingEditRequest;
import delivery.stork.repository.TravelerRepo;
import delivery.stork.service.TravelerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelerServiceImpl implements TravelerService {
    private final TravelerRepo travelerRepo;
    private final TravelerMapper travelerMapper;
    @Override
    public TravelerDto postService(ServiceRequest serviceRequest, User userTraveler) {
        Traveler serviceTraveler = travelerMapper.toTraveler(serviceRequest,userTraveler);
        travelerRepo.save(serviceTraveler);
        return travelerMapper.toTravelerDto(serviceTraveler);
    }

    @Override
    public void deleteTraveling(Long id, User userTraveler) {
        Traveler traveling  = travelerRepo.findById(id).orElseThrow(()->
                new NotFoundException("No such a traveler found with ID " + id));
        if (!traveling.getUserTraveler().equals(userTraveler))
            throw new NotAllowedException("You are allowed to delete it!");
        travelerRepo.delete(traveling);
    }

    @Override
    public ResponseEntity<List<TravelerDto>> getTravelingServices() {
        List<Traveler> travelerList = travelerRepo.findAll();
        return ResponseEntity.ok(travelerList.stream().map(travelerMapper::toTravelerDto)
                .collect(Collectors.toList()));
    }

    @Override
    public TravelerDto updateTraveling(TravelingEditRequest travelingEditRequest, User traveler) {
        Traveler traveling = travelerRepo.findTravelerByUserTraveler(traveler).orElseThrow(()->
                new NotFoundException("No such a traveling with traveler " + traveler.getFullName() ));
        Traveler updatedTraveling = travelerMapper.toTravelingFromEdit(travelingEditRequest,traveling);
        travelerRepo.save(updatedTraveling);
        return travelerMapper.toTravelerDto(updatedTraveling);
    }

}
