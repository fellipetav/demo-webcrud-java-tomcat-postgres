package com.example.crudproject.demo.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.crudproject.demo.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * @param inexactName
     * @return a list of objects User where the attribute name's value contains the parameter inexactName.
     */
    @Query(value = "select user from User user where lower(trim(user.name)) like %?1%")
    List<User> searchByInexactName(String inexactName);
}
