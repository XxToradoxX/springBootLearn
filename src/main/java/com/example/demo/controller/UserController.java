package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public Result SelectAll() {
        List<User> userList = userService.SelectAll();
        return Result.success(userList);
    }

    @DeleteMapping("/{familyId}")
    public Result DeleteFamilyByIdAndAllItsUsers(@PathVariable("familyId") Integer familyId) {
        Integer flag = userService.DeleteFamilyByIdAndAllItsUsers(familyId);
        if(flag != null){
            return Result.success(flag);
        }else{
            return Result.error("一个都没删啊一个都没");
        }
    }
}
