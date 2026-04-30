package africa.wheel.wheelgame.repository;

import africa.wheel.wheelgame.model.CitazioniRarita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitazioniRaritaRepository extends JpaRepository<CitazioniRarita, Long> {
}
