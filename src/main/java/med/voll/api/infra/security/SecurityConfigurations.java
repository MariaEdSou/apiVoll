package med.voll.api.infra.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    //SecurityFilterChain  usado p configurar coisas relacionadas a config e autenticaco
    // csrf().disable().desabilita contra ataque csrf
    //  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().build() p falar p spring que vc quer uma autenticacao statelles
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .build();
    }
//ele lanca uma exception
//o metodo ensina p spring como injeta os objetos
//bean serve p exportar uma classe para o spring fazendo com que ele consiga carrelar e realizar as injecoes de dependencia em outras classes
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
// config do BCrypt
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
