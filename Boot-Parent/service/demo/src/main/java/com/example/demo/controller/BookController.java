package com.example.demo.controller;

import com.example.demo.pojo.Book;
import com.example.demo.pojo.Result;
import com.example.demo.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping()
    public Result bookList(@RequestBody Map<String, Integer> map) {
        // pageNum:当前页，pageSize:每页的显示的数据数目
        PageHelper.startPage(map.get("pageNumber"), map.get("pageSize"));
        List<Book> bookList = bookService.SelectAll();
        // 上面两行代码必须在一起，设置.startPage()方法后立即查询数据
        PageInfo<Book> pageInfo = new PageInfo<>(bookList);
        // 获得分页后的数据信息
        return Result.success(pageInfo);
//        List<Book> bookList = bookService.SelectAll();
//        log.info("bookList接口调用返回: {}", bookList);
//        return Result.success(bookList);
    }

    @PostMapping("/{id}")
    public Result getBookById(@PathVariable("id") Integer id) {
        Book book = bookService.SelectById(id);
        log.info("bookId调用返回: {}", book);
        return Result.success(book);
    }

}
