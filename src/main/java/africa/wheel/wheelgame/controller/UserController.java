package africa.wheel.wheelgame.controller;

import africa.wheel.wheelgame.model.User;
import africa.wheel.wheelgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/money")
    public ResponseEntity<User> updateMoney(@RequestBody Map<String, Double> request, Authentication authentication) {
        String email = authentication.getName();
        Double amount = request.get("money");
        return ResponseEntity.ok(userService.updateMoney(email, amount));
    }
}
