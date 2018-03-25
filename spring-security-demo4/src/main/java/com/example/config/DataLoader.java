package com.example.config;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
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
        logger.info("The sample data has been generated");

        alreadySetup = true;
    }

}
