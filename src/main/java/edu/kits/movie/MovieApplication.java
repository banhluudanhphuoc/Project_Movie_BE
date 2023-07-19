package edu.kits.movie;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "API for project", version = "v1.0.0"),
        servers = {
                @Server(url = "http://localhost:8080", description = "Default Server URL"),
        })
//@SecurityScheme(
//        name = "Bearer Authentication",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        scheme = "bearer"
//)
@SpringBootApplication
public class MovieApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
//    @Bean
//    CommandLineRunner init(FileStorageService storageService) {
//        return (args) -> {
//            storageService.init();
//        };
//    }
}
