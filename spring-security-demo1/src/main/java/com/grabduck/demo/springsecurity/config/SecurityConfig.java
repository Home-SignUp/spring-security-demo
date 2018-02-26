package com.grabduck.demo.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // эта аннотация отключает конфигурированию секюрности из коробки...
public class SecurityConfig extends WebSecurityConfigurerAdapter { // но можно использовтаь механизм адаптера

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
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
}
