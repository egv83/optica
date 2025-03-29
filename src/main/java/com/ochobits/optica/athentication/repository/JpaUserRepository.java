package com.ochobits.optica.athentication.repository;

import com.ochobits.optica.athentication.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByUserName(String user);

    @Query(value = "SELECT IFNULL(MAX(o.id),0)+1 FROM operador o",nativeQuery = true)
    Long findMaxId();

}
