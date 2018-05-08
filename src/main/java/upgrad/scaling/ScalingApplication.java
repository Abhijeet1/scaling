package upgrad.scaling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ScalingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScalingApplication.class, args);
	}
}
