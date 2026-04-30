package africa.wheel.wheelgame.service;

import africa.wheel.wheelgame.config.KafkaConfig;
import africa.wheel.wheelgame.dto.CitationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(name = "spring.kafka.enabled", havingValue = "true")
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = KafkaConfig.CITATION_TOPIC, groupId = "wheel-game-group")
    public void handleCitationExtracted(CitationEvent event) {
        log.info("Ricevuto evento Kafka per citazione estratta da: {}", event.getUsername());
        
        // Invia il messaggio a tutti i client sottoscritti al topic /topic/notifications
        messagingTemplate.convertAndSend("/topic/notifications", event);
    }
}
