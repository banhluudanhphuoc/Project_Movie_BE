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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//                .antMatchers("/swagger-ui/**", "/v3/api-docs/**");
//    }
    private final RestAuthenticationEntryPoint unauthorizedHandler;
    private final CustomUserDetail userDetail;
    private final PasswordEncoder passwordEncoder;
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetail)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
        http.authorizeRequests()
                .antMatchers("/api/login", "/api/token/refresh",
                        "/api/un/**", "/auth/**",
                        "/oauth2/**", "/api/verification-otp/**",
                        "/api/resend-otp/**", "/api/register",
                        "/greeting/**", "/topic/server/**")
                .permitAll()
                .antMatchers("/api/user/**")
                .hasAnyAuthority("USER", "ADMIN", "SUPER_ADMIN")
                .antMatchers("/api/admin/**")
                .hasAnyAuthority("ADMIN", "SUPER_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback")
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);
        http.httpBasic().disable();
        http.formLogin().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
