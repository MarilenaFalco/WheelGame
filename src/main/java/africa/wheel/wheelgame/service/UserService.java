package africa.wheel.wheelgame.service;

import africa.wheel.wheelgame.dto.LoginRequest;
import africa.wheel.wheelgame.dto.RegisterRequest;
import africa.wheel.wheelgame.model.User;
import africa.wheel.wheelgame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {
        log.info("Tentativo di registrazione per username: {}", request.getUsername());
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username già in uso");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email già in uso");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .money(0.0)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        String inputIdentifier = request.getUsername();
        log.info("Tentativo di login per: {}", inputIdentifier);
        
        User user = userRepository.findByUsername(inputIdentifier)
                .or(() -> userRepository.findByEmail(inputIdentifier))
                .orElseThrow(() -> new RuntimeException("Credenziali non valide"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("Password errata per: {}", inputIdentifier);
            throw new RuntimeException("Credenziali non valide");
        }

        log.info("Login effettuato con successo per: {}", user.getEmail());
        return user;
    }

    public User updateMoney(String username, Double money) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con username: " + username));
        user.setMoney(money);
        return userRepository.save(user);
    }
}
