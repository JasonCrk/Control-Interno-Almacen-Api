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
			Usuario user1 = Usuario.builder()
					.firstName("Vaneza Bernardet")
					.lastName("Asteaga Vega")
					.phoneNumber("123 456 789")
					.dni("12345678")
					.email("vanezabernadetasteagavega@gmail.com")
					.password(passwordEncoder.encode("1234"))
					.avatarUrl("https://static.vecteezy.com/system/resources/previews/009/292/244/original/default-avatar-icon-of-social-media-user-vector.jpg")
					.role(Role.JEFE_UNIDAD_LOGISTICA)
					.build();
			userRepository.save(user1);

			Usuario user2 = Usuario.builder()
					.firstName("Emerzon Javier")
					.lastName("Kolki Martinez")
					.phoneNumber("989 897 634")
					.dni("11111111")
					.email("emerzonkolki@gmail.com")
					.password(passwordEncoder.encode("1234"))
					.avatarUrl("https://static.vecteezy.com/system/resources/previews/009/292/244/original/default-avatar-icon-of-social-media-user-vector.jpg")
					.role(Role.TECNICO_ADMINISTRATIVO_LOGISTICA)
					.build();
			userRepository.save(user2);

			Usuario user3 = Usuario.builder()
					.firstName("Arturo Erick")
					.lastName("Anticona Garcia")
					.phoneNumber("222 222 222")
					.dni("22222222")
					.email("arturoerick@gmail.com")
					.password(passwordEncoder.encode("1234"))
					.avatarUrl("https://static.vecteezy.com/system/resources/previews/009/292/244/original/default-avatar-icon-of-social-media-user-vector.jpg")
					.role(Role.JEFE_UNIDAD_FINANZAS)
					.build();
			userRepository.save(user3);

			Usuario user4 = Usuario.builder()
					.firstName("Gladys Lizbeth")
					.lastName("Giron Guerrero")
					.phoneNumber("333 333 333")
					.dni("33333333")
					.email("gladyslizbeth@gmail.com")
					.password(passwordEncoder.encode("1234"))
					.avatarUrl("https://static.vecteezy.com/system/resources/previews/009/292/244/original/default-avatar-icon-of-social-media-user-vector.jpg")
					.role(Role.ASISTENTE)
					.build();
			userRepository.save(user4);

			Usuario user5 = Usuario.builder()
					.firstName("Alfredo")
					.lastName("de la Torre")
					.phoneNumber("444 444 444")
					.dni("44444444")
					.email("alfredotorre@gmail.com")
					.password(passwordEncoder.encode("1234"))
					.avatarUrl("https://static.vecteezy.com/system/resources/previews/009/292/244/original/default-avatar-icon-of-social-media-user-vector.jpg")
					.role(Role.ANALISTA_FINANZAS)
					.build();
			userRepository.save(user5);

			Usuario user6 = Usuario.builder()
					.firstName("María")
					.lastName("Lopez Mendoza")
					.phoneNumber("555 555 555")
					.dni("55555555")
					.email("marialopezmendoza@gmail.com")
					.password(passwordEncoder.encode("1234"))
					.avatarUrl("https://static.vecteezy.com/system/resources/previews/009/292/244/original/default-avatar-icon-of-social-media-user-vector.jpg")
					.role(Role.TECNICO_ADMINISTRATIVO_ALMACEN)
					.build();
			userRepository.save(user6);
		};
	}
}
