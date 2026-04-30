package africa.wheel.wheelgame.service;

import africa.wheel.wheelgame.model.Citazioni;
import africa.wheel.wheelgame.repository.CitazioniRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitazioniService {

    private final CitazioniRepository citazioniRepository;

    public List<Citazioni> getAllCitazioni() {
        return citazioniRepository.findAll();
    }
}
