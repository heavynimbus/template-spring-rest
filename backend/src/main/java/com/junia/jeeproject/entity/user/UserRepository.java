package com.junia.jeeproject.entity.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  Optional<UserEntity> findByNameAndPasswordLike(String name, String password);
}
