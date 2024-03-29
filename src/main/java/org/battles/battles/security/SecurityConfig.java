package org.battles.battles.security;

import lombok.RequiredArgsConstructor;
import org.battles.battles.security.jwt.JwtAuthenticationFilter;
import org.battles.battles.user.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthService authService;

    private final CorConfig corConfig;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
            .antMatchers("/swagger-ui/index.html", "/h2-console", "/h2-console/*")
            .antMatchers("/api/user/signin", "/api/user/signup", "/api/user/all",
                "/api/user/signup/*");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .apply(new customDsl())
            .and()
            .authorizeRequests()
            .antMatchers("/api/user/**").access("hasRole('ROLE_USER')")
            .anyRequest().permitAll()
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(authService),
                UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();
        return http.build();
    }

    public class customDsl extends AbstractHttpConfigurer<customDsl, HttpSecurity> {

        @Override
        public void configure(HttpSecurity http) {
            AuthenticationManager authenticationManager = http.getSharedObject(
                AuthenticationManager.class);
            http
                .addFilter(corConfig.corsFilter());
        }

    }
}
