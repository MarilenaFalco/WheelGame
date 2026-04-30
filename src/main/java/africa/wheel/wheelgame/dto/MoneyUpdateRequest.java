package africa.wheel.wheelgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyUpdateRequest {
    private String username;
    private Double money;
}
