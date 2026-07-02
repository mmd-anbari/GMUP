package org.example.gmup.adapter.outbound.jpa;

import org.example.gmup.adapter.outbound.entity.UserEntity;
import org.example.gmup.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> getUserEntityById(Long id);
}
