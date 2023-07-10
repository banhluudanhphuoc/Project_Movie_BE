package edu.kits.movie.Security;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kits.movie.Model.Response.DecodeJWTResponse;
import edu.kits.movie.Security.JWT.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/login")
                ||
                request.getServletPath().equals("/api/register")) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    String token = authorizationHeader.substring("Bearer ".length());// lấy token
                    DecodeJWTResponse jwtResponse = jwtUtils.decodeJWT(token);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtResponse.getName(), jwtResponse.getAuthority());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    log.info("Error logging in: {}", e.getMessage());
                    response.setHeader("error", e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    //response.sendError(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
                filterChain.doFilter(request, response);
            }
        }
    }
}
