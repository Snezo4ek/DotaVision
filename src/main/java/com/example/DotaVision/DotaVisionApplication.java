package com.example.DotaVision;

import com.example.DotaVision.user.User;
import com.example.DotaVision.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DotaVisionApplication {


	public static void main(String[] args) {
		SpringApplication.run(DotaVisionApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository users, PasswordEncoder encoder){
		try {
			return args->{
				users.save(new User("user", encoder.encode("user"), "ROLE_USER"));
				users.save(new User("admin", encoder.encode("admin"), "ROLE_ADMIN"));
			};
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		return args-> {

		};
	}

}
