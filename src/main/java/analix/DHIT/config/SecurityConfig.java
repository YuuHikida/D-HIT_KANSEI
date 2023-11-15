package analix.DHIT.config;
<<<<<<< HEAD
=======

>>>>>>> 904382eb5e9dcad171c624fe2b02591eb19deedd
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
<<<<<<< HEAD
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
=======
>>>>>>> 904382eb5e9dcad171c624fe2b02591eb19deedd
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("SHA-256");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll()

        ).logout(
                logout -> logout
<<<<<<< HEAD
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
=======
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
>>>>>>> 904382eb5e9dcad171c624fe2b02591eb19deedd
        ).authorizeHttpRequests(auth -> auth
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/manager/**").hasRole("MANAGER")
                .requestMatchers("/member/**").hasRole("MEMBER")
                .anyRequest().authenticated()
<<<<<<< HEAD
        );
        return http.build();
    }

=======
        ).exceptionHandling(ex -> ex.accessDeniedPage("/"));

        return http.build();
    }


>>>>>>> 904382eb5e9dcad171c624fe2b02591eb19deedd
}


