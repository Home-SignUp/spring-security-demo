package com.grabduck.demo.springsecurity.service;

import com.google.common.collect.ImmutableList;
import com.grabduck.demo.springsecurity.domain.Role;
import com.grabduck.demo.springsecurity.domain.User;
import com.grabduck.demo.springsecurity.persistence.UserDao;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void init() {
//        if (!userDao.findByUsername("user").isPresent()) {
//            userDao.save(
//                    User.builder()
//                            .username("user")
//                            .password("password")
//                            .authorities(ImmutableList.of(Role.USER))
//                            .accountNonExpired(true)
//                            .accountNonLocked(true)
//                            .credentialsNonExpired(true)
//                            .enabled(true)
//                            .build());
//        }

        userDao.findByUsername("user").ifPresent(user -> {
            user.setPassword(new BCryptPasswordEncoder().encode("password"));
            userDao.save(user);
        });
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username)
            throws UsernameNotFoundException
    {
//        return User.builder()
//                .username(username)
//                .password("password")
//                .authorities(ImmutableList.of(Role.USER))
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .credentialsNonExpired(true)
//                .enabled(true)
//                .build();

        return userDao.findByUsername(username)
//                .orElse(null); // здесь Spring говорит что пользователь НЕбыл найден (но это НЕхорошо)
                .orElseThrow(() ->
                new UsernameNotFoundException("user " + username + " was not found!")); // здесь Spring говорит что наша попытка залогиниться была НЕправильная по причине что имя пользователя или пароль были НЕверны (тут Spring делает умно - это хорошо)
    }
}
