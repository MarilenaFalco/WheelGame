package africa.wheel.wheelgame.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConditionalOnProperty(name = "spring.kafka.enabled", havingValue = "true")
public class KafkaConfig {

    public static final String CITATION_TOPIC = "citation-extracted";

    @Bean
    public NewTopic citationTopic() {
        return TopicBuilder.name(CITATION_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
