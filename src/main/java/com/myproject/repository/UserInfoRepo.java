package com.myproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.entities.UserInfo;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {

	Optional<UserInfo> findByuserName(String username);

}
