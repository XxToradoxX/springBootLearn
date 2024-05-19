package com.example.demo.controller;

import com.example.demo.pojo.Book;
import com.example.demo.pojo.Result;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping()
    public Result bookList() {
        List<Book> bookList = bookService.SelectAll();
        return Result.success(bookList);
    }
}
