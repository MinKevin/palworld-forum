package online.palworldkorea.palworldkorea_online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PalworldkoreaOnlineApplication {
	public static void main(String[] args) {
		SpringApplication.run(PalworldkoreaOnlineApplication.class, args);
	}

}
