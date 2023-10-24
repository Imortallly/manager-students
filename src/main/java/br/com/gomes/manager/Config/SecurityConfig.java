package br.com.gomes.manager.Config;

import br.com.gomes.manager.Model.Users;
import br.com.gomes.manager.Service.RegisterValidationService;
import br.com.gomes.manager.Service.UserDetailsService;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity

public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                authorizeConfig -> {
                    authorizeConfig.requestMatchers("/login/").permitAll();
                    authorizeConfig.requestMatchers("/register/").permitAll();
                    authorizeConfig.requestMatchers("/css/**").permitAll();
                    authorizeConfig.requestMatchers("/img/**").permitAll();
                    authorizeConfig.requestMatchers("/js/**").permitAll();
                    authorizeConfig.requestMatchers("/terms/").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                })
                .formLogin(formLoginConfig -> {
                    formLoginConfig
                            .loginPage("/login/")
                            .permitAll()
                            .defaultSuccessUrl("/home/");
                })
                .logout(logout -> {
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout/"))
                            .logoutSuccessUrl("/login/").permitAll();
                })
                .build();
    }
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


}
