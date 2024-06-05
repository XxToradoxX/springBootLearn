package com.example.springLearn.service;

import com.example.springLearn.pojo.User;

import java.util.List;

public interface UserService {

    List<User> SelectAll();

    Integer DeleteFamilyByIdAndAllItsUsers(Integer familyId);
}
