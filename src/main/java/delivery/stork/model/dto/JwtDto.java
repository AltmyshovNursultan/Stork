package delivery.stork.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDto {
    private Long id;
    private String fullName;
    private String email;
    private String accessToken;
    private String tokenType;
}
