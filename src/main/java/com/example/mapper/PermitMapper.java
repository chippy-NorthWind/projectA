package com.example.mapper;

import com.example.pojo.Permit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
@ResponseBody
public interface PermitMapper {
    List<Permit> selectAuthByUserName(String name);

    List<Permit> selectAllAuth();

    void givePtoUserById(int pid, int uid);

    void addPermit(String permitName);

    List<String> selectAuthByUsernameInString(String name);

    void deleteByUsername(String username);

    void deleteByUserId(int id);
}
