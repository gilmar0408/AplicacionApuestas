package com.co.masivian.bet.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.masivian.bet.api.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

}
