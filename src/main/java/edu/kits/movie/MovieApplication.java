package edu.kits.movie;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

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
        ClassPathResource serviceAccount = new ClassPathResource(
                "movie-1fb03-firebase-adminsdk-c1twk-7f70b5fa97.json");
        try{
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                    .setStorageBucket("bonik-f7b39.appspot.com")
                    .build();
            FirebaseApp.initializeApp(options);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
//    @Bean
//    CommandLineRunner init(FileStorageService storageService) {
//        return (args) -> {
//            storageService.init();
//        };
//    }
}
