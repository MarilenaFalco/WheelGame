package africa.wheel.wheelgame.repository;

import africa.wheel.wheelgame.model.UserCitazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCitazioniRepository extends JpaRepository<UserCitazioni, Long> {
}
