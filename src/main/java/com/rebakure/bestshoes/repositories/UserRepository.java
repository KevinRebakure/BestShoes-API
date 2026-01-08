package com.rebakure.bestshoes.repositories;

import com.rebakure.bestshoes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
