package com.example.service;

import com.example.mapper.PermitMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.Permit;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class UserServiceImpl implements UserService {
    UserMapper userMapper;
    PermitMapper permitMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPermitMapper(PermitMapper permitMapper) {
        this.permitMapper = permitMapper;
    }

    @Override
    public List<Permit> selectAuthByUserName(String name) {
        return permitMapper.selectAuthByUserName(name);
    }

    @Override
    public List<String> selectAuthByUserNameInString(String name) {
        return permitMapper.selectAuthByUsernameInString(name);
    }

    @Override
    public void givePtoUserById(int pid, int uid) {
        permitMapper.givePtoUserById(pid, uid);
    }

    @Override
    public void addPermit(String permitName) {
        permitMapper.addPermit(permitName);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public User selectUserByNameForBasic(String name) {
        return userMapper.selectUserByNameForBasic(name);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteUserById(int id) {
        permitMapper.deleteByUserId(id);
        userMapper.deleteUserById(id);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void addUserWithPermits(User user, List<Integer> permitId) {
        userMapper.addUser(user);
        for (Integer i : permitId) {
            permitMapper.givePtoUserById(i, user.getId());
        }
    }

    @Override
    public void deletePermitByUsername(String username) {
        permitMapper.deleteByUsername(username);
    }


    @Override
    public List<Permit> selectAllPermit() {
        return permitMapper.selectAllAuth();
    }


}
