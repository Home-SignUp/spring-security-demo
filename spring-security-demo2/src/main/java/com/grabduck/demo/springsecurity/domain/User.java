package com.grabduck.demo.springsecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@Builder // https://urvanov.ru/2015/09/20/lombok-builder ** https://stackoverflow.com/questions/9424364/cant-compile-project-when-im-using-lombok-under-intellij-idea ** https://easyjava.ru/java/lombok/builder-v-odnu-stroku ** http://qaru.site/questions/416715/lombok-builder-not-initializing-collections
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users2")
public class User implements UserDetails {
    @Id private ObjectId id;
    @Indexed(unique = true) private List<Role> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
