package com.ning.adminmanage.controller;

import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.model.SysRole;
import com.ning.adminmanage.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("/all")
    @ResponseBody
    public Results<SysRole> getAll(){
        return roleService.getAllRoles();
    }

}
