package com.ning.adminmanage.controller;

import com.ning.adminmanage.base.result.PageTableRequest;
import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.dto.UserDto;
import com.ning.adminmanage.model.SysUser;
import com.ning.adminmanage.service.UserService;
import com.ning.adminmanage.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 分页查询User
     * @param pageTableRequest
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Results<SysUser> getUsers(PageTableRequest pageTableRequest){
        pageTableRequest.countOffset();
        return userService.getAllUsersByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
    }

    /**
     * 跳转到user-add页面
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String addUser(Model model){
       model.addAttribute(new SysUser());
        return "user/user-add";
    }

    /**
     * 跳转到user-add页面
     * @param userDto
     * @param roleId
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Results<SysUser> saveUser(UserDto userDto,Integer roleId){
        //用户是否可用，默认让他可用
        userDto.setStatus(1);
        //密码用MD5加密
        userDto.setPassword(MD5.crypt(userDto.getPassword()));
        return userService.save(userDto,roleId);
    }
    //对日期字符串转化
    String pattern = "yyyy-MM-dd";
    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(pattern), true));// CustomDateEditor为自定义日期编辑器，允许为空值
    }

}
