package com.ning.adminmanage.controller;

import com.alibaba.fastjson.JSONArray;
import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("permission")
@Slf4j
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 所有的permission的数据，通过Jsonarray菜单树返回
     * @return
     */
    @RequestMapping(value = "/listAllPermission", method = RequestMethod.GET)
    @ResponseBody
    public Results<JSONArray> listAllPermission() {
        return permissionService.listAllPermission();
    }
}
