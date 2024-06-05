package com.example.springLearn.mapper;

import com.example.springLearn.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> SelectAll();

    Integer DeleteFamilyByIdAndAllItsUsers(Integer familyId);
}
