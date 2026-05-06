package africa.wheel.wheelgame.service;

import africa.wheel.wheelgame.dto.CitationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final org.springframework.beans.factory.ObjectProvider<KafkaTemplate<String, Object>> kafkaTemplateProvider;

    @Async
    public void sendCitationEvent(CitationEvent event) {
        kafkaTemplateProvider.ifAvailable(template -> {
            try {
                template.send(africa.wheel.wheelgame.config.KafkaConfig.CITATION_TOPIC, event)
                        .whenComplete((result, ex) -> {
                            if (ex != null) {
                                log.error("Errore asincrono Kafka durante l'invio: {}", ex.getMessage());
                            } else {
                                log.info("Messaggio inviato a Kafka con successo");
                            }
                        });
            } catch (Exception e) {
                log.warn("Kafka non raggiungibile (timeout metadati): {}", e.getMessage());
            }
        });
    }
}
