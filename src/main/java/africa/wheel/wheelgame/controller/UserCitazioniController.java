package africa.wheel.wheelgame.controller;

import africa.wheel.wheelgame.dto.UserCitazioneRequest;
import africa.wheel.wheelgame.model.UserCitazioni;
import africa.wheel.wheelgame.service.UserCitazioniService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-citazioni")
@RequiredArgsConstructor
public class UserCitazioniController {

    private final UserCitazioniService userCitazioniService;

    @PostMapping("/aggiungiCitazioni")
    public ResponseEntity<UserCitazioni> aggiungiCitazione(
            @RequestBody UserCitazioneRequest request,
            Authentication authentication) {
        
        String email = authentication.getName();
        UserCitazioni saved = userCitazioniService.aggiungiCitazione(email, request);
        return ResponseEntity.ok(saved);
    }
}
