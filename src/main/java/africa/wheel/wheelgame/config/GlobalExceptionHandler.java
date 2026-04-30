package africa.wheel.wheelgame.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> body = new HashMap<>();
        String message = ex.getMessage();
        
        // Se il messaggio inizia con '[' e finisce con ']', lo trattiamo come una lista di errori (formato String della lista)
        if (message != null && message.startsWith("[") && message.endsWith("]")) {
            body.put("errors", message.substring(1, message.length() - 1).split(", "));
        } else {
            body.put("error", message);
        }
        
        return ResponseEntity.badRequest().body(body);
    }
}
