package myapp.Security;

import dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDao userDao= new UserDao();
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .authorities("ROLE_USER")
                .and()
                .withUser("admin")
                .password("admin")
                .authorities("ROLE_ADMIN");
        for (int i=0;i<userDao.findAll().size();i++){
            auth.inMemoryAuthentication().withUser(userDao.findAll().get(i).GetUser_username()).password(userDao.findAll().get(i).GetUser_password()).authorities("ROLE_USER");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/**").permitAll()
                .and().formLogin();
    }
}