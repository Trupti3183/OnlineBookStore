package com.example.dao;

import com.example.entity.User;

public interface UserDao {
    User findById(Long id);
    void save(User user);
    void update(User user);
    void delete(User user);
}