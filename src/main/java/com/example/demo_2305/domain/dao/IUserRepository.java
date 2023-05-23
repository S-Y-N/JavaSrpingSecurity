package com.example.demo_2305.domain.dao;

import com.example.demo_2305.domain.UserDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<UserDTO, Long> {

   @Query("SELECT u FROM UserDTO u where u.firstName = :name")
    UserDTO findByName(String name);

   @Query("SELECT u FROM UserDTO u where u.email = :email")
   UserDTO findByEmail(String email);
}
