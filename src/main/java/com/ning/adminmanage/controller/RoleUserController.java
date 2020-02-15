package com.ning.adminmanage.controller;

import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.model.SysRole;
import com.ning.adminmanage.service.RoleUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("roleuser")
@Slf4j
public class RoleUserController {
    @Autowired
    private RoleUserService roleUserService;

    /**
     * 根据userid在roleuser表中查询role的值
     * @return
     */
    @PostMapping("/getRoleUserByUserId")
    public Results getRoleUserByUserId(Integer userId){
        log.info("RoleUserController.getRoleUserByUserId: param="+userId);
        return roleUserService.getSysRoleUserByUserId(userId);
    }
}
