package africa.wheel.wheelgame.controller;

import africa.wheel.wheelgame.model.Citazioni;
import africa.wheel.wheelgame.service.CitazioniService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/citazioni")
@RequiredArgsConstructor
public class CitazioniController {

    private final CitazioniService citazioniService;

    @GetMapping("/getCitazioni")
    public ResponseEntity<List<Citazioni>> getAllCitazioni() {
        return ResponseEntity.ok(citazioniService.getAllCitazioni());
    }
}
