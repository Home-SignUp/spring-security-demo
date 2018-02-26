package com.grabduck.demo.springsecurity.persistence;

import com.grabduck.demo.springsecurity.domain.User;
import com.grabduck.demo.springsecurity.domain.UserField;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public Optional<User> findByUsername(@NonNull String username) {
        return Optional.ofNullable(
                mongoTemplate.findOne(
                        query(where(UserField.USER_NAME.field()).is(username)),
                        User.class));
    }

    public void save(@NonNull User user) {
        mongoTemplate.save(user);
    }
}
