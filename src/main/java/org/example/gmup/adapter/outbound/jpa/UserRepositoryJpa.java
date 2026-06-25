package org.example.gmup.adapter.outbound.jpa;

import org.example.gmup.adapter.outbound.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {
}
