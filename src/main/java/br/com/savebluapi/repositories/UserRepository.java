package br.com.savebluapi.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.savebluapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByTelephone(String telephone);
    boolean existsByEmail(String email);
    boolean existsByTelephone(String telephone);
    
    List<User> findByName(String name);
    
}