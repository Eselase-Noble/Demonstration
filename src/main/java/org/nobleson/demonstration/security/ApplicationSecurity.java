package org.nobleson.demonstration.security;

import lombok.RequiredArgsConstructor;
import org.nobleson.demonstration.repositories.StudentRepository;
import org.nobleson.demonstration.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurity {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    /**
     * The UserDetailsService bean which retrieves user details from the user repository
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        //The lambda returns fetches the user  details by username
        return username ->  userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * An AuthenticationProvider bean that uses the UserDetailsService and PasswordEncoder
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //Set the custom UserDetailService
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        //Set the PasswordEncoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * An Authentication Manager bean that retrieves the AuthenticationManager from the configuration
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Define a PasswordEncoder bean which uses BCrypt hashing
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
