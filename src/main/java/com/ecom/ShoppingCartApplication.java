package com.ecom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecom.model.UserDtls;
import com.ecom.repository.UserRepository;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

    @Bean
    CommandLineRunner createAdmin(UserRepository userRepo, PasswordEncoder encoder) {
		return args -> {
			if (userRepo.findByEmail("admin@gmail.com") == null) {
				UserDtls admin = new UserDtls();
				admin.setName("Admin");
				admin.setEmail("admin@gmail.com");
				admin.setPassword(encoder.encode("admin123"));
				admin.setRole("ROLE_ADMIN"); // match your role naming convention
				
				admin.setIsEnable(true);
				// Add more fields if your UserDtls requires them (e.g., address, phone)
				userRepo.save(admin);
				System.out.println("✅ Admin user created.");
			} else {
				System.out.println("ℹ️ Admin already exists.");
			}
		};
	}
}
