package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> SelectAll() {
        return userMapper.SelectAll();
    }

    @Override
    public Integer DeleteFamilyByIdAndAllItsUsers(Integer familyId) {
        return userMapper.DeleteFamilyByIdAndAllItsUsers(familyId);
    }
}
