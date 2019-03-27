package pl.arturzaczek.school.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/employee/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/employee/login?status=error")
                .loginProcessingUrl("/employee/loggedIn")
                .defaultSuccessUrl("/index")
                .and()
                .logout()
                .logoutUrl("/employee/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT u.email, u.password, 1 FROM employee e WHERE e.email = ? ")
                .authoritiesByUsernameQuery("SELECT e.email, r.role_name " +
                        "FROM employee e " +
                        "JOIN role r ON e.role_id = r.id " +
                        "WHERE e.email = ?")
                .passwordEncoder(passwordEncoder)
        ;
    }
}
