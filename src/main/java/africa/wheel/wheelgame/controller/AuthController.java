package africa.wheel.wheelgame.controller;

import africa.wheel.wheelgame.dto.LoginRequest;
import africa.wheel.wheelgame.dto.RegisterRequest;
import africa.wheel.wheelgame.model.User;
import africa.wheel.wheelgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
}
