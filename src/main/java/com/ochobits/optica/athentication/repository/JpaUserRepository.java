package com.ochobits.optica.athentication.repository;

import com.ochobits.optica.athentication.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByUserName(String user);
}
