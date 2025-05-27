package org.lesson.java.spring_pizzeria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // serve a dire a spring che deve usare usare questa configurazione per tutte le richieste web security
public class SecurityConfiguration {
    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests()
        .requestMatchers("/pizzas/create" , "/pizzas/edit/**" , "/pizzas/*/offerta" ).hasAnyAuthority("ADMIN")
        .requestMatchers("/pizzas/create" , "/pizzas/edit/**" ).hasAnyAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "/pizzas/**").hasAnyAuthority("ADMIN")
        .requestMatchers( "/ingredienti/create").hasAnyAuthority("ADMIN")
        .requestMatchers( "/ingredienti/*").hasAnyAuthority("USER","ADMIN")
        .requestMatchers( "/ingredienti/*/**").hasAnyAuthority("ADMIN")
        .requestMatchers( "/offerte/**").hasAnyAuthority("ADMIN")
        .requestMatchers("/pizzas" , "/pizzas/**").hasAnyAuthority("USER" , "ADMIN")
        .requestMatchers("/**").permitAll()
        .and().formLogin()
        .and().logout()
        .and().exceptionHandling();


        return http.build();
        
    }
    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        //recuper degli utenti via username
        authProvider.setUserDetailsService(userDetailService());

        //metodo per il password encoder
        authProvider.setPasswordEncoder(passwordEncoder());


        return authProvider;
    }
    @Bean DatabaseUserDetailService userDetailService(){
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}
