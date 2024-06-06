package com.example.demo.service;

import com.example.demo.mapper.BookMapper;
import com.example.demo.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookMapper bookMapper;
    @Override
    public List<Book> SelectAll() {
        return bookMapper.selectAll();
    }

    @Override
    public Book SelectById(Integer id) {
        return bookMapper.selectByid(id);
    }
}
