package com.zuzi.ipproxy.dao;

import com.zuzi.ipproxy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserDao extends JpaRepository<User,Long> {

}