package africa.wheel.wheelgame.controller;

import africa.wheel.wheelgame.dto.UserCitazioneRequest;
import africa.wheel.wheelgame.model.UserCitazioni;
import africa.wheel.wheelgame.projection.UserCitazioniProjection;
import africa.wheel.wheelgame.service.UserCitazioniService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-citazioni")
@RequiredArgsConstructor
public class UserCitazioniController {

    private final UserCitazioniService userCitazioniService;

    @PostMapping("/aggiungiCitazioni")
    public ResponseEntity<UserCitazioni> aggiungiCitazione(
            @RequestBody UserCitazioneRequest request) {
        if (request.getEmail() == null) {
            return ResponseEntity.status(401).build();
        }
        UserCitazioni saved = userCitazioniService.aggiungiCitazione(request.getEmail(), request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/userCitazioni")
    public ResponseEntity<List<UserCitazioniProjection>> retrieveCitazioni(@RequestParam String email){
        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(userCitazioniService.getUserCitazioniByEmail(email));
    }
}
