package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
@ResponseBody
public interface UserMapper {
    List<User> selectAllUser();

    User selectUserById(int id);

    User selectUserByName(String name);

    User selectUserByNameForBasic(String name);

    void deleteUserById(int ud);

    void addUser(User user);

    void updateUser(User user);
}
