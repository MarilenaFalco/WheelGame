package africa.wheel.wheelgame.service;

import africa.wheel.wheelgame.dto.LoginRequest;
import africa.wheel.wheelgame.dto.RegisterRequest;
import africa.wheel.wheelgame.model.User;
import africa.wheel.wheelgame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Map<String, List<String>> loginErrors = new ConcurrentHashMap<>();

    public User register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username già in uso");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email già in uso");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        String inputIdentifier = request.getUsername();
        
        Optional<User> userOpt = userRepository.findByUsername(inputIdentifier);
        if (userOpt.isEmpty()) {
            userOpt = userRepository.findByEmail(inputIdentifier);
        }

        // Usiamo l'email trovata nel DB come chiave per i tentativi, 
        // così se l'utente alterna tra username e email il conteggio persiste.
        // Se l'utente non esiste, usiamo l'input originale.
        String trackingKey = userOpt.isPresent() ? userOpt.get().getEmail() : inputIdentifier;

        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            List<String> errors = loginErrors.computeIfAbsent(trackingKey, k -> new ArrayList<>());
            int attempt = errors.size() + 1;
            
            String message;
            if (attempt == 1) {
                message = "hai sbagliato MIIIINCHIA";
            } else if (attempt == 2) {
                message = "MA LO HAI FATTO IL CORSO???";
            } else {
                message = "ALTRO GIRO ALTRA CORSA";
            }
            
            errors.add(message);
            throw new RuntimeException(errors.toString());
        }

        loginErrors.remove(trackingKey);
        return userOpt.get();
    }

}
