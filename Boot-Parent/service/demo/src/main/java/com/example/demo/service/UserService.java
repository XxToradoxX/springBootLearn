package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.List;

public interface UserService {

    List<User> SelectAll();

    Integer DeleteFamilyByIdAndAllItsUsers(Integer familyId);
}
