package com.grabduck.demo.springsecurity.config;

import com.grabduck.demo.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // эта аннотация отключает конфигурирования секюрности из коробки...
public class SecurityConfig extends WebSecurityConfigurerAdapter { // можно использовтаь механизм адаптера

    /*
     * (минус) форма аудитенфикации через сессию плоха тем что она НЕрасширяема при маштабировании, например:
     * в случае высокой нагрузки добавляем несколько серверов приложений... файл-сессия которая будет создана в пространстве одного сервера приложения - отсутствует в пространстве другого сервера приложения
     * И когда в следствии нагрузки 'Лоад-Балансер' перекинет реквест пользователя с одного сервера приложения на другой сервер приложений - в этом случае потеряется файл-сессии пользователя...
     *
     * Поэтому всю такую информацию сервер приложений НЕдолжен сохранять в сессии, а такая информация приходит из клиентским запросом (STATELESS-состояние)
     * (и для этого случая базовая вудитенфикация работает нормально, но только в защищенных каналах HTTPS)
     */
    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/readme.txt", "/css/*").permitAll()
                .anyRequest().authenticated()
                .and()
//                .formLogin().permitAll() // здесь определенно - что мы работаем через СЕССИЮ (такой метод подходит только для тех случаев когда мы наше представление генерим на сервере)
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
    }

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                userDetailsService(userService)
                .passwordEncoder(bcryptPasswordEncoder()); // Encoded password does not look like BCrypt
    }
}
