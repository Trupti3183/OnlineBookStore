package com.example.service;

import java.util.List;

import com.example.entity.Book;

public interface BookService {
	Book findById(Long id);
    List<Book> findAll();
    void save(Book book);
    void update(Book book);
    void delete(Book book);
}
