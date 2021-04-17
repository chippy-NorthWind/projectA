package com.example.service;

import com.example.pojo.Permit;
import com.example.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface UserService {

    /**
     * @param name:用户名
     * @return 返回功能的字符串集合
     * @apiNote 用用户名对数据库进行查询
     */
    List<Permit> selectAuthByUserName(String name);

    List<String> selectAuthByUserNameInString(String name);

    void givePtoUserById(int pid, int uid);

    void addPermit(String permitName);

    List<User> selectAllUser();

    User selectUserById(int id);

    User selectUserByName(String name);

    User selectUserByNameForBasic(String name);

    void deleteUserById(int id);

    void addUser(User user);

    void updateUser(User user);

    void addUserWithPermits(User user, List<Integer> permitId);

    void deletePermitByUsername(String username);

    List<Permit> selectAllPermit();
}
