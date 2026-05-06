package africa.wheel.wheelgame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WheelGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(WheelGameApplication.class, args);
    }
}
