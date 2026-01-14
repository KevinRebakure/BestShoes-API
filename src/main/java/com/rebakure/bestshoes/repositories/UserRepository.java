package com.rebakure.bestshoes.repositories;

import com.rebakure.bestshoes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findUserById(Long id);
}
