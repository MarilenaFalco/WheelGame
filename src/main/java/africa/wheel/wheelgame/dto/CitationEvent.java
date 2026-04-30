package africa.wheel.wheelgame.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitationEvent {
    private String username;
    private String citationText;
    private String rarity;
}
