//package edu.kits.movie.Security;
//
//import edu.kits.movie.Controller.Router.Api;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers(Api.AdminApi.USER_ADMIN + "/movies").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//        return http.build();
//    }
//
//
//}
//
//
//
//
