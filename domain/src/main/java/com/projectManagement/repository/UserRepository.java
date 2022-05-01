package com.projectManagement.repository;

import com.projectManagement.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAll();

     List<UserEntity> findAllByStatus(String status);

    List<UserEntity> findAllByRole(String role);

    Optional<UserEntity> findAllByIdAndStatus(Long id, String userRole);

    Optional<UserEntity> findByLogin(String login);

    @Modifying
    @Query("UPDATE UserEntity user SET user.lastName = ?2, user.firstName = ?3, user.patronymic = ?4," +
        " user.birthday = ?5, user.login = ?6, user.password = ?7, user.money = ?8 WHERE user.id = ?1")
    Integer updateUser(Long userId, String lastName, String firstName, String patronymic,
                       Date birthday, String login, String password, Float money);

    @Modifying
    @Query("UPDATE UserEntity user SET user.lastName = ?2 WHERE user.id = ?1")
    Long updateLastName(Long userId, String lastName);

    @Modifying
    @Query("UPDATE UserEntity userEntity SET userEntity.status = ?2 WHERE userEntity.id = ?1")
    Integer updateUserStatus(Long userId, String userStatus);

    void deleteById(Long userId);

    @Modifying
    @Query("UPDATE UserEntity userEntity SET userEntity.money = ?2 WHERE userEntity.id = ?1")
    Integer updateBalance(Long userId, Float amount);
}