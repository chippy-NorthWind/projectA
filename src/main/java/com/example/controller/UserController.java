package com.example.controller;

import com.example.pojo.Permit;
import com.example.pojo.User;
import com.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 橙鼠鼠
 */
@Controller
public class UserController {
    static final String LOGIN_FAIL = "/user/login";
    static final String MSG = "msg";
    static final String PERMITS = "permits";
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/user/toUpdate/{id}"})
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        User user = userService.selectUserById(id);
        List<Permit> permits = userService.selectAllPermit();
        List<Permit> list = userService.selectAuthByUserName(user.getUsername());
        HashMap<String, String> map = new HashMap<>();
        for (Permit permit : list) {
            map.put(permit.getPermitName(), null);
        }
        model.addAttribute("userMsg", user);
        model.addAttribute(PERMITS, permits);
        model.addAttribute("userPermit", map);
        return "user/update";
    }

    @PostMapping({"/user/update"})
    @Transactional(rollbackFor = {Exception.class})
    public String userUpdate(User user, String[] permits,Model model) {
        userService.updateUser(user);
        userService.deletePermitByUsername(user.getUsername());
        for (String permit : permits) {
            userService.givePtoUserById(Integer.parseInt(permit), user.getId());
        }
        System.out.println("用户更新完成" + permits.toString() + " " + user.toString());


        List<User> userList = userService.selectAllUser();
        Map<String, String> permitList = this.getPermitList(userList);
        model.addAttribute("users", userList);
        model.addAttribute(PERMITS, permitList);
        return "/user/list";
    }

    @ResponseBody
    public Map<String,String> getPermitList(List<User> userList){
        String temp;
        HashMap<String, String> permitMap = new HashMap<>(userList.size());
        for (User user : userList) {
            List<String> pList=userService.selectAuthByUserNameInString(user.getUsername());
            temp="";
            for (String s : pList) {
                temp+=s+" ";
            }
            permitMap.put(user.getUsername(),temp);
        }
        return permitMap;
    }

    @PostMapping({"/user/add"})
    @Transactional(rollbackFor = {Exception.class})
    public String userAdd(User user, String[] permits) {
        List<Integer> list = new ArrayList<>();
        for (String permitId : permits) {
            list.add(Integer.parseInt(permitId));
        }
        userService.addUserWithPermits(user, list);
        System.out.println("插入用户" + user.toString());
        return "user/add";
    }


    @GetMapping({"/user/login"})
    public String userLogin(String username, String password, Model model, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            session.setAttribute("LoginUser", token.getUsername());
            System.out.println("登录成功");
            return "redirect:/index";
        } catch (UnknownAccountException e) {
            model.addAttribute(MSG, "未知用户名");
            return LOGIN_FAIL;
        } catch (IncorrectCredentialsException e) {
            model.addAttribute(MSG, "密码错误");
            return LOGIN_FAIL;
        } catch (Exception e) {
            model.addAttribute(MSG, "Bad request");
            return LOGIN_FAIL;
        }
    }

    @GetMapping({"/user/toList"})
    public String toUserList(Model model) {
        List<User> userList = userService.selectAllUser();
        Map<String, String> permitList = this.getPermitList(userList);
        model.addAttribute("users", userList);
        model.addAttribute(PERMITS, permitList);
        return "user/list";
    }

    @GetMapping({"/user/toAdd"})
    public String toAdd(Model model) {
        List<Permit> permit = userService.selectAllPermit();
        model.addAttribute(PERMITS, permit);
        return "user/add";
    }

    @GetMapping({"/user/delete/{id}"})
    public String deleteUser(Model model,@PathVariable("id")int id){
        userService.deleteUserById(id);

        List<User> userList = userService.selectAllUser();
        Map<String, String> permitList = this.getPermitList(userList);
        model.addAttribute("users", userList);
        model.addAttribute(PERMITS, permitList);
        return "user/list";
    }

}
