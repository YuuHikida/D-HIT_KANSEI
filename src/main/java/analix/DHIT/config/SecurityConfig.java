//package analix.DHIT.config;
//
//import lombok.RequiredArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@Configuration
//@RequiredArgsConstructor
////@EnableMethodSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll();
//        return http.build();
//    }
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests()
////                .requestMatchers(HttpMethod.POST,"/admin/**").hasRole("ADMIN")
////                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
////                .anyRequest().permitAll();
////                .defaultSuccessUrl("/success");
////        return http.build();
////    }
//////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//////        http
//////                .authorizeHttpRequests()
//////                .anyRequest().authenticated()
//////                .and()
//////                .formLogin()
//////                .defaultSuccessUrl("/success");
//////        return http.build();
//////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//}
