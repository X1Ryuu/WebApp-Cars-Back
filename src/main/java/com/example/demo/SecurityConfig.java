package com.example.demo;
/*
import com.example.demo.jwt.CustomJwt;
import com.example.demo.jwt.CustomJwtConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // Publiczne endpointy
                        .anyRequest().authenticated()             // Wszystkie inne wymagają autoryzacji
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwtConfigurer ->
                                jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                );


        http.cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(customJwtConverter())
                        )
                );


        http.cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**").permitAll()

                        //.requestMatchers("/h2-console/**").permitAll()  // Zezwolenie na dostęp do konsoli H2
                       // .requestMatchers("/brands/all/**").permitAll()     // Przykładowe publiczne endpointy
                       // .anyRequest().fullyAuthenticated()                // Wszystkie inne wymagają autoryzacji
                    //    .requestMatchers("/brands/**").hasAuthority("ROLE_admin")
                      //  .anyRequest().hasAuthority("ROLE_admin")
                       // .requestMatchers("/brands/add").hasRole("admin")
                   //     .requestMatchers("/brands/add").hasAnyAuthority("admin", "ROLE_admin")
                        .requestMatchers("/brands/all").hasRole("admin")
                        .requestMatchers("/brands/**").hasRole("admin")
                        .anyRequest().authenticated()
                        
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(customJwtConverter())
                        )
                )
                .csrf(AbstractHttpConfigurer::disable) // Wyłączenie CSRF dla konsoli H2
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }

    private Converter<Jwt, CustomJwt> customJwtConverter() {
        return new CustomJwtConverter();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Obsługa wszystkich endpointów
                        .allowedOrigins("http://localhost:4200") // Dozwolone originy
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Dozwolone metody HTTP
                        .allowedHeaders("*") // Dozwolone nagłówki
                        .allowCredentials(true); // Wsparcie dla uwierzytelniania z ciasteczkami

            }
        };
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // Dostosowanie konwertera, np. mapowanie ról (opcjonalne)
        return converter;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Dekoder dla Keycloak (lub innego dostawcy OAuth2)
        String issuerUri = "http://localhost:9090/realms/my-realm"; // Zmień na odpowiednią wartość
        return JwtDecoders.fromIssuerLocation(issuerUri);
    }
}*/

import com.example.demo.jwt.CustomJwt;
import com.example.demo.jwt.CustomJwtConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/brands/update", "/brands/delete/**", "/brands/add/**",
                                "/models/add/**", "/versions/add/**").hasRole("admin")
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(customJwtConverter())
                                //  .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                )
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }


    private Converter<Jwt, CustomJwt> customJwtConverter() {
        return new CustomJwtConverter();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true); // Allow credentials (e.g., cookies)
            }
        };
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        String issuerUri = "http://localhost:9090/realms/my-realm";
        return JwtDecoders.fromIssuerLocation(issuerUri);
    }





}

