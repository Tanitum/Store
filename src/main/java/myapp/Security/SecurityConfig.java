package myapp.Security;

import dao.SellerDao;
import dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDao userDao = new UserDao();
        SellerDao sellerDao = new SellerDao();
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("user"))
                .authorities("ROLE_USER")
                .and()
                .withUser("seller")
                .password(passwordEncoder.encode("seller"))
                .authorities("ROLE_SELLER")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities("ROLE_ADMIN");

        for (int i = 0; i < userDao.findAll().size(); i++) {
            auth.inMemoryAuthentication().withUser(userDao.findAll().get(i).GetUser_username()).password(userDao.findAll().get(i).GetUser_password()).authorities("ROLE_USER");
        }
        for (int j = 0; j < sellerDao.findAll().size(); j++) {
            auth.inMemoryAuthentication().withUser(sellerDao.findAll().get(j).GetSeller_sellername()).password(sellerDao.findAll().get(j).GetSeller_password()).authorities("ROLE_SELLER");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/seller/**").hasAnyAuthority("ROLE_SELLER", "ROLE_ADMIN")
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/**").permitAll()
                .and().formLogin();
    }
}