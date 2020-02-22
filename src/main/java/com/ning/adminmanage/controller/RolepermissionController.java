package com.ning.adminmanage.controller;


import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.dto.RoleDto;
import com.ning.adminmanage.model.SysPermission;
import com.ning.adminmanage.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("rolepermission")
@Slf4j
public class RolepermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/listAllPermissionByRoleId", method = RequestMethod.GET)
    @ResponseBody
    public Results<SysPermission> listAllPermissionByRoleId(RoleDto roleDto){
        log.info(getClass().getName() + " : param =  " + roleDto);
        return permissionService.listByRoleId(roleDto.getId().intValue());
    }
}
