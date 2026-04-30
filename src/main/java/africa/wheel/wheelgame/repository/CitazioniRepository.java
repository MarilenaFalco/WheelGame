package africa.wheel.wheelgame.repository;

import africa.wheel.wheelgame.model.Citazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitazioniRepository extends JpaRepository<Citazioni, Long> {
}
