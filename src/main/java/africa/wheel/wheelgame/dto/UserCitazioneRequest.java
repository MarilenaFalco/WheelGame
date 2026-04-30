package africa.wheel.wheelgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCitazioneRequest {
    private Long citazioneId;
    private Double money;
}
