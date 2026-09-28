package org.example.gmup.adapter.outbound.jpa;

import org.example.gmup.adapter.outbound.entity.UserEntity;
import org.example.gmup.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByUsername(String username);

    boolean existsByUsername(String username);

    Optional<UserEntity> getUserEntityById(Long id);


    @Query("SELECT u.storageLimitLeft FROM UserEntity u WHERE u.id = :id")
    Long getUserStorageLimitById(@Param("id") Long id);


    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.storageLimitLeft = :storageLimit WHERE u.id = :id")
    void updateUserStorageLimitById(@Param("id") long id, @Param("storageLimit") long storageLimit);
}
