package com.example.demo.controller;

import com.example.demo.pojo.Book;
import com.example.demo.pojo.Result;
import com.example.demo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping()
    public Result bookList() {
        List<Book> bookList = bookService.SelectAll();
        log.info("bookList接口调用返回: {}", bookList);
        return Result.success(bookList);
    }

    @PostMapping("/{id}")
    public Result getBookById(@PathVariable("id") Integer id) {
        Book book = bookService.SelectById(id);
        log.info("bookId调用返回: {}", book);
        return Result.success(book);
    }

}
