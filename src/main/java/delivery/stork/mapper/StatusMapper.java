package delivery.stork.mapper;

import delivery.stork.model.dto.StatusDto;
import delivery.stork.model.entity.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {
    public StatusDto toDto(Status status) {
        return StatusDto.builder()
                .id(status.getId())
                .status(status.getStatus())
                .build();
    }
}
