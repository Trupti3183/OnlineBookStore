package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(User user);
	User getUserById(Long userId);
}
