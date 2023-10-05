package com.ADS2.controlinternoalmacenapi;

import com.ADS2.controlinternoalmacenapi.model.enums.Role;
import com.ADS2.controlinternoalmacenapi.model.Usuario;
import com.ADS2.controlinternoalmacenapi.repository.UsuarioRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ControlInternoAlmacenApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlInternoAlmacenApiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			UsuarioRepository userRepository,
			PasswordEncoder passwordEncoder
	) {
		return args -> {
			Usuario adminUser = Usuario.builder()
					.firstName("Vaneza Bernardet")
					.lastName("Asteaga Vega")
					.phoneNumber("123 456 789")
					.dni("12345678")
					.email("vanezabernadetasteagavega@gmail.com")
					.password(passwordEncoder.encode("1234qwerasdf"))
					.avatarUrl("https://static.vecteezy.com/system/resources/previews/009/292/244/original/default-avatar-icon-of-social-media-user-vector.jpg")
					.role(Role.ADMIN)
					.build();

			userRepository.save(adminUser);
		};
	}
}
