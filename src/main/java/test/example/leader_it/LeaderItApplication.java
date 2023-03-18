package test.example.leader_it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude= HibernateJpaAutoConfiguration.class)
public class LeaderItApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaderItApplication.class, args);
	}

}

