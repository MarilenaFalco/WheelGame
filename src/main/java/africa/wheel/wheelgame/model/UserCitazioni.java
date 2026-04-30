package africa.wheel.wheelgame.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_citazioni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCitazioni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "citazione_id", referencedColumnName = "id", nullable = false)
    private Citazioni citazione;

    @ManyToOne
    @JoinColumn(name = "rarita_valore", referencedColumnName = "id", nullable = false)
    private CitazioniRarita rarita;
}
