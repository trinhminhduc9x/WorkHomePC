package com.book.service;

import com.book.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();

    Book findById(Integer id);

    void save(Book book);
}
