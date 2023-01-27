package delivery.stork.service.impls;

import delivery.stork.exception.InvalidDataException;
import delivery.stork.exception.NotAllowedException;
import delivery.stork.exception.NotFoundException;
import delivery.stork.mapper.TravelerMapper;
import delivery.stork.model.dto.TravelerDto;
import delivery.stork.model.entity.Traveling;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.SearchRequest;
import delivery.stork.model.wrapper.ServiceRequest;
import delivery.stork.model.wrapper.TravelingEditRequest;
import delivery.stork.repository.TravelerRepo;
import delivery.stork.service.TravelerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelerServiceImpl implements TravelerService {
    private final TravelerRepo travelerRepo;
    private final TravelerMapper travelerMapper;
    @Override
    public TravelerDto postService(ServiceRequest serviceRequest, User userTraveler) {
        if(!validateDate(serviceRequest.getFlightDate(),serviceRequest.getArrivalDate()))
            throw new InvalidDataException("Please enter proper date!");

        Traveling serviceTraveler = travelerMapper.toTraveler(serviceRequest,userTraveler);
        travelerRepo.save(serviceTraveler);
        return travelerMapper.toTravelerDto(serviceTraveler);
    }
    private boolean validateDate(LocalDateTime startDate, LocalDateTime deadline) {
        return !startDate.isEqual(deadline) && !startDate.isAfter(deadline);
    }

    @Override
    public void deleteTraveling(Long id, User userTraveler) {
        Traveling traveling  = travelerRepo.findById(id).orElseThrow(()->
                new NotFoundException("No such a traveler found with ID " + id));
        if (!traveling.getUserTraveler().getId().equals(userTraveler.getId()))
            throw new NotAllowedException("You are allowed to delete it!");
        travelerRepo.delete(traveling);
    }

    @Override
    public ResponseEntity<List<TravelerDto>> getTravelingServices() {
        List<Traveling> travelerList = travelerRepo.findAll();
        return ResponseEntity.ok(travelerList.stream().map(travelerMapper::toTravelerDto)
                .collect(Collectors.toList()));
    }

    @Override
    public TravelerDto updateTraveling(Long id, TravelingEditRequest travelingEditRequest, User traveler) {
        if(!validateDate(travelingEditRequest.getFlightDate(),travelingEditRequest.getArrivalDate()))
            throw new InvalidDataException("Please enter proper date!");

        Traveling traveling = travelerRepo.findById(id).orElseThrow(()-> new NotFoundException("Not found."));

        if (!traveling.getUserTraveler().getId().equals(traveler.getId()))
            throw new NotAllowedException("You are allowed to update it!");

        Traveling updatedTraveling = travelerMapper.toTravelingFromEdit(travelingEditRequest,traveling);
        travelerRepo.save(updatedTraveling);
        return travelerMapper.toTravelerDto(updatedTraveling);
    }

    @Override
    public List<TravelerDto> searchTraveling(SearchRequest searchRequest) {
        List<Traveling> travelerList = travelerRepo.findPackagesByFilter(searchRequest.getFromCity(), searchRequest.getToCity());
        return travelerList.stream().map(travelerMapper::toTravelerDto).collect(Collectors.toList());
    }

}
