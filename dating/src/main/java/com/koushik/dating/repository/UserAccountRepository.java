package com.koushik.dating.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koushik.dating.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
	Optional<UserAccount> findByEmail(String email);
	boolean existsByEmail(String email);
}