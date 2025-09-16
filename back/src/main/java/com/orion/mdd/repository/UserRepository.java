package com.orion.mdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orion.mdd.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
