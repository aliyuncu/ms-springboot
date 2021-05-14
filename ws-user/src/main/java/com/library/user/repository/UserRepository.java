package com.library.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(Long userId);
}
