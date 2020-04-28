package uom.niroshan.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Niroshan")
                .password("Niroshan")
                .roles("USER")
                .and()
                .withUser("Niroshan1")
                .password("Niroshan1")
                .roles("ADMIN");

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
//authorization part
    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http.authorizeRequests()
             .antMatchers("/admin").hasRole("ADMIN")//this need to be done by most restricted to least restricted
             .antMatchers("/user").hasAnyRole("ADMIN","USER")//if /user url is accesible by USER and ADMIN
             .antMatchers("/").permitAll()//if "/"
             .and().formLogin();

    }
}
