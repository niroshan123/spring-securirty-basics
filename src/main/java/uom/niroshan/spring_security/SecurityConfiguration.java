package uom.niroshan.spring_security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity//says that this is web security configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("niroshan1")
                .password("niroshan1")
                .roles("USER")
        .and()
                .withUser("niroshan2")
                .password("niroshan2")
                .roles("ADMIN");


    }

    @Bean
    public PasswordEncoder getPasswodEncoder(){
        return NoOpPasswordEncoder.getInstance();    }
}