package com.gelin.mysqltransaction.dao;

import com.gelin.mysqltransaction.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

}

