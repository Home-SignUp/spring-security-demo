package com.grabduck.demo.springsecurity;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

public class ApplicationTest {

    /**
     * "Basic dXNlcjpwYXNzd29yZA==" // 'Basic' - здесь это аудитентификатор, говорящий о том что это базовая аудитентификация
     *
     * проблема такого метода в том, что имя пользователя и пароль НЕшифруются
     * (минус) Есдинственное место где удобно применять такую аудитенфикацию - это внутри защищенного соединения (HTTPS)
     */
    @Test
    public void configureGlobal()
            throws Exception {
        String auth = "dXNlcjpwYXNzd29yZA=="; // user:password
        System.out.println( new String(Base64.getDecoder().decode(auth)) );

        System.out.println(new BCryptPasswordEncoder().encode("password")); // $2a$10$er2A/9U85aLud9XJeXta8.QykeToU5Rpt.IJsqfuW8gWKs1zPHqR.
    }

}
