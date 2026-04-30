package africa.wheel.wheelgame.controller;

import africa.wheel.wheelgame.dto.MoneyUpdateRequest;
import africa.wheel.wheelgame.model.User;
import africa.wheel.wheelgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/money")
    public ResponseEntity<User> updateMoney(@RequestBody MoneyUpdateRequest request) {
        return ResponseEntity.ok(userService.updateMoney(request.getUsername(), request.getMoney()));
    }
}
