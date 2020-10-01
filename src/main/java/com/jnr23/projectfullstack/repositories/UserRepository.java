package com.jnr23.projectfullstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jnr23.projectfullstack.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
