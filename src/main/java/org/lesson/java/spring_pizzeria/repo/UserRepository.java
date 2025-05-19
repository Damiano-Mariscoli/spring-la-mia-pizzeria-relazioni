package org.lesson.java.spring_pizzeria.repo;

import java.util.Optional;

import org.lesson.java.spring_pizzeria.model.User;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByUsername(String username);
}
