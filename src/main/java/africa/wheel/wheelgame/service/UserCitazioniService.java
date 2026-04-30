package africa.wheel.wheelgame.service;

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

@Service
@RequiredArgsConstructor
public class UserCitazioniService {

    private final UserCitazioniRepository userCitazioniRepository;
    private final UserRepository userRepository;
    private final CitazioniRepository citazioniRepository;

    @Transactional
    public UserCitazioni aggiungiCitazione(String email, UserCitazioneRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con email: " + email));

        Citazioni citazione = citazioniRepository.findById(request.getCitazioneId())
                .orElseThrow(() -> new RuntimeException("Citazione non trovata con ID: " + request.getCitazioneId()));

        UserCitazioni userCitazioni = UserCitazioni.builder()
                .user(user)
                .citazione(citazione)
                .money(request.getMoney())
                .rarita(citazione.getRarity())
                .build();

        return userCitazioniRepository.save(userCitazioni);
    }
}
