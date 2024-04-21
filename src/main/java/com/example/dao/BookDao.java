package com.example.dao;

import com.example.entity.Book;

public interface BookDao {
    Book findById(Long id);
    void save(Book book);
    void update(Book book);
    void delete(Book book);
}