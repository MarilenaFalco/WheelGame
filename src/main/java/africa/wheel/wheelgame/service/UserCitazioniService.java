package africa.wheel.wheelgame.service;

import africa.wheel.wheelgame.dto.CitationEvent;
import africa.wheel.wheelgame.dto.UserCitazioneRequest;
import africa.wheel.wheelgame.model.Citazioni;
import africa.wheel.wheelgame.model.User;
import africa.wheel.wheelgame.model.UserCitazioni;
import africa.wheel.wheelgame.projection.UserCitazioniProjection;
import africa.wheel.wheelgame.repository.CitazioniRepository;
import africa.wheel.wheelgame.repository.UserCitazioniRepository;
import africa.wheel.wheelgame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCitazioniService {

    private final UserCitazioniRepository userCitazioniRepository;
    private final UserRepository userRepository;
    private final CitazioniRepository citazioniRepository;
    private final KafkaProducerService kafkaProducerService;

    @Value("${spring.kafka.enabled:false}")
    private boolean kafkaEnabled;

    @Transactional
    public UserCitazioni aggiungiCitazione(String email, UserCitazioneRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con email: " + email));

        Citazioni citazione = citazioniRepository.findById(request.getCitazioneId())
                .orElseThrow(() -> new RuntimeException("Citazione non trovata con ID: " + request.getCitazioneId()));

        UserCitazioni userCitazioni = UserCitazioni.builder()
                .user(user)
                .citazione(citazione)
                .rarita(citazione.getRarity())
                .build();

        user.setMoney(request.getMoney());
        userRepository.save(user);
        UserCitazioni saved = userCitazioniRepository.save(userCitazioni);

        // Invio evento per notifica via thread asincrono separato per non bloccare la transazione
        if (kafkaEnabled) {
            CitationEvent event = CitationEvent.builder()
                    .username(user.getUsername())
                    .citationText(citazione.getTesto())
                    .rarity(citazione.getRarity() != null ? citazione.getRarity().getNome() : "Unknown")
                    .build();
            
            kafkaProducerService.sendCitationEvent(event);
        }

        return saved;
    }

    public List<UserCitazioniProjection> getUserCitazioniByEmail(String email){
        return userCitazioniRepository.findCitazioneIdAndRaritaValoreByUserEmail(email);
    }
}
