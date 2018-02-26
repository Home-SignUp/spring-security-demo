package com.grabduck.demo.springsecurity;

import org.junit.Test;

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
    }

}
