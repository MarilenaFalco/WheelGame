package africa.wheel.wheelgame.repository;

import africa.wheel.wheelgame.model.UserCitazioni;
import africa.wheel.wheelgame.projection.UserCitazioniProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCitazioniRepository extends JpaRepository<UserCitazioni, Long> {
    @Query("SELECT uc.citazione.id as citazioneId, uc.rarita.valore as raritaValore, c.testo as testoCitazione " +
           "FROM UserCitazioni uc JOIN Citazioni c ON uc.citazione.id = c.id WHERE uc.user.email = :email")
    List<UserCitazioniProjection> findCitazioneIdAndRaritaValoreByUserEmail(@Param("email") String email);
}
