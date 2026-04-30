package africa.wheel.wheelgame.service;

import africa.wheel.wheelgame.config.KafkaConfig;
import africa.wheel.wheelgame.dto.CitationEvent;
import africa.wheel.wheelgame.dto.UserCitazioneRequest;
import africa.wheel.wheelgame.model.Citazioni;
import africa.wheel.wheelgame.model.User;
import africa.wheel.wheelgame.model.UserCitazioni;
import africa.wheel.wheelgame.repository.CitazioniRepository;
import africa.wheel.wheelgame.repository.UserCitazioniRepository;
import africa.wheel.wheelgame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCitazioniService {

    private final UserCitazioniRepository userCitazioniRepository;
    private final UserRepository userRepository;
    private final CitazioniRepository citazioniRepository;
    private final org.springframework.beans.factory.ObjectProvider<org.springframework.kafka.core.KafkaTemplate<String, Object>> kafkaTemplateProvider;

    @org.springframework.beans.factory.annotation.Value("${spring.kafka.enabled:false}")
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

        User userUpdate = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .money(request.getMoney())
                .build();
        userRepository.save(userUpdate);
        UserCitazioni saved = userCitazioniRepository.save(userCitazioni);

        // Invio evento Kafka
        if (kafkaEnabled) {
            kafkaTemplateProvider.ifAvailable(template -> {
                CitationEvent event = CitationEvent.builder()
                        .username(user.getUsername())
                        .citationText(citazione.getTesto())
                        .rarity(citazione.getRarity() != null ? citazione.getRarity().getNome() : "Unknown")
                        .build();
                
                template.send(KafkaConfig.CITATION_TOPIC, event);
            });
        }

        return saved;
    }

    public List<UserCitazioni> getUserCitazioniByEmail(String email){
        return userCitazioniRepository.findUserCitazionisByUserEmail(email);
    }
}
