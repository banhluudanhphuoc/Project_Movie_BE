package edu.kits.movie.Config;

import edu.kits.movie.Security.CustomUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final RestAuthenticationEntryPoint unauthorizedHandler;
    private final CustomUserDetail userDetail;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetail)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
        http.authorizeHttpRequests()
                .antMatchers("/api/login", "/api/register", "/swagger-ui/**", "/v3/api-docs/**")
                .permitAll()
                .antMatchers("/api/v1/admin/**")
                .hasAnyAuthority("ADMIN")
                .antMatchers("/api/v1/**")
                .hasAnyAuthority("USER", "ADMIN")
                .anyRequest()
                .authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

}



