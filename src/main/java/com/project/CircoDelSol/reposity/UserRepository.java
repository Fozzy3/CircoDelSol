package com.project.CircoDelSol.reposity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.CircoDelSol.entity.Award;
import com.project.CircoDelSol.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{
    Optional<User> findByIdentity(Integer identity);

}

