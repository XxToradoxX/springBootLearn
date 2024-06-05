package com.example.springLearn.service;

import com.example.springLearn.pojo.Book;

import java.util.List;

public interface BookService {
    public List<Book> SelectAll();
    Book SelectById(Integer id);
}
