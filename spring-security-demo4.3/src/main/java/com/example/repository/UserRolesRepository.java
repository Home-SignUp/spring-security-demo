package com.example.repository;

import com.example.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {

	@Query("SELECT a.role FROM UserRole a, User b WHERE b.userName=?1 AND a.userId=b.id")
    List<String> findRoleByUserName(String userName);

}