package com.example.config;

import com.example.domain.Employee;
import com.example.domain.User;
import com.example.domain.UserRole;
import com.example.repository.EmployeeRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserRolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private boolean alreadySetup = false;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    /**
     * User #1:  (priya|priya) - ограниченный доступ
     * User #2:  (naveen|naveen) - открытый доступ
     */
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup) {
            return;
        }

        employeeRepository.save(new Employee("Gustavo", "Ponce", true));
        employeeRepository.save(new Employee("John", "Smith", true));
        employeeRepository.save(new Employee("Jim ", "Morrison", false));
        employeeRepository.save(new Employee("David", "Gilmour", true));

        userRepository.save(
                getNewUser(1l, "priya", "$2a$04$CO93CT2ObgMiSnMAWwoBkeFObJlMYi/wzzOnPlsTP44r7qVq0Jln2", "abc@abc.com", 1));
        userRepository.save(
                getNewUser(2l, "naveen", "$2a$04$j3JpPUp6CTAe.kMWmdRNC.Wie58xDNPfcYz0DBJxWkucJ6ekJuiJm", "def@def.com", 1));

        userRolesRepository.save(getNewUserRole(101l, "ROLE_USER", 1l));
        userRolesRepository.save(getNewUserRole(102l, "ROLE_ADMIN", 2l));
        userRolesRepository.save(getNewUserRole(103l, "ROLE_USER", 2l));

        logger.info("The sample data has been generated");
        alreadySetup = true;
    }

    @Transactional
    private final User getNewUser(Long id, String userName, String password, String email, int enabled) {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setEnabled(enabled);
        return user;
    }

    @Transactional
    private final UserRole getNewUserRole(Long id, String role, Long userId) {
        UserRole userRole = new UserRole();
        userRole.setId(id);
        userRole.setRole(role);
        userRole.setUserId(userId);
        return userRole;
    }

}
