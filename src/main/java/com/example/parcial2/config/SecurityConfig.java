package com.example.parcial2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/error", "/error/403").permitAll()
                        .requestMatchers("/consultas/nuevo", "/consultas/eliminar/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/consultas/editar/**", "/consultas/guardar")
                        .hasAnyRole("ADMINISTRADOR", "PACIENTE")
                        .requestMatchers("/", "/consultas").hasAnyRole("ADMINISTRADOR", "MEDICO", "PACIENTE")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/consultas", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception.accessDeniedPage("/error/403"));

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails administrador = User.withDefaultPasswordEncoder()
                .username("ADMIN-999")
                .password("secreto123")
                .roles("ADMINISTRADOR")
                .build();

        UserDetails medico = User.withDefaultPasswordEncoder()
                .username("MED-000")
                .password("secreto123")
                .roles("MEDICO")
                .build();

        UserDetails diego = User.withDefaultPasswordEncoder()
                .username("PAC-101")
                .password("secreto123")
                .roles("PACIENTE")
                .build();

        UserDetails marta = User.withDefaultPasswordEncoder()
                .username("PAC-102")
                .password("secreto123")
                .roles("PACIENTE")
                .build();

        UserDetails juan = User.withDefaultPasswordEncoder()
                .username("PAC-103")
                .password("secreto123")
                .roles("PACIENTE")
                .build();

        return new InMemoryUserDetailsManager(administrador, medico, diego, marta, juan);
    }
}
