package africa.wheel.wheelgame.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "citazioni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Citazioni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String testo;

    @ManyToOne
    @JoinColumn(name = "rarity_valore", referencedColumnName = "valore", nullable = false)
    private CitazioniRarita rarity;
}
