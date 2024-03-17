package com.shankar.curdopreation_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shankar.curdopreation_api.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
