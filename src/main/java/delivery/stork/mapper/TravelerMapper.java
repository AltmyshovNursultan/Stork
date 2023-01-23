package delivery.stork.mapper;

import delivery.stork.model.dto.TravelerDto;
import delivery.stork.model.entity.Traveler;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.ServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TravelerMapper {
    private final UserMapper userMapper;
    public Traveler toTraveler(ServiceRequest serviceRequest, User userTraveler) {
        return  Traveler.builder()
                .getFrom(serviceRequest.getGetFrom())
                .getTo(serviceRequest.getGetTo())
                .flightDate(serviceRequest.getFlightDate())
                .arrivalDate(serviceRequest.getArrivalDate())
                .price(serviceRequest.getPrice())
                .capacity(serviceRequest.getCapacity())
                .userTraveler(userTraveler)
                .build();
    }

    public TravelerDto toTravelerDto(Traveler serviceTraveler) {
        return  TravelerDto.builder()
                .id(serviceTraveler.getId())
                .getFrom(serviceTraveler.getGetFrom())
                .getTo(serviceTraveler.getGetTo())
                .flightDate(serviceTraveler.getFlightDate())
                .arrivalDate(serviceTraveler.getArrivalDate())
                .capacity(serviceTraveler.getCapacity())
                .price(serviceTraveler.getPrice())
                .userTravelerDto(userMapper.toUserDto(serviceTraveler.getUserTraveler()))
                .build();
    }
}
