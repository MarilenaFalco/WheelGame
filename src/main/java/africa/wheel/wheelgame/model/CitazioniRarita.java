package africa.wheel.wheelgame.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "citazioni_rarita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitazioniRarita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true)
    private Integer valore;
}
