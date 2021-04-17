package com.example;

import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProjectApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        List<String> list = userService.selectAuthByUserNameInString("root");
        for (String s : list) {
            System.out.println("=======>" + s);
        }
    }

}
