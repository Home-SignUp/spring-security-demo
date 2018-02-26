package com.grabduck.demo.springsecurity.domain;

import org.junit.Test;

public class MyUserTest {

    @Test
    public void test() {
        MyUser myUser = MyUser.builder()
                .name("Aaa")
                .ago(1)
                .build();
    }
}
