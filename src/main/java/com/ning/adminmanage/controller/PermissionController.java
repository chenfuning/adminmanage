package com.ning.adminmanage.controller;

import com.alibaba.fastjson.JSONArray;
import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.model.SysPermission;
import com.ning.adminmanage.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

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
    @PreAuthorize("hasAuthority('sys:menu:query')")
    public Results<JSONArray> listAllPermission() {
        return permissionService.listAllPermission();
    }

    /**
     * 获取permission的所有值
     * @return
     */
    @GetMapping("/menuAll")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:query')")
    public Results getMenuAll(){
        return permissionService.getMenuAll();
    }

    @GetMapping("/add")
    public String addPermission(Model model){
        model.addAttribute("sysPermission",new SysPermission());
        return "permission/permission-add";
    }
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:add')")
    public Results savePermission(@RequestBody SysPermission sysPermission){
        return permissionService.save(sysPermission);
    }

    @GetMapping("/edit")
    public String editPermission(Model model, SysPermission permission) {
        model.addAttribute("sysPermission",permissionService.getSysPermissionById(permission.getId()));
        return "permission/permission-add";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    public Results updatePermission(@RequestBody  SysPermission permission) {
        return permissionService.updateSysPermission(permission);
    }

    /**
     * 删除菜单，包括菜单下的按钮
     * @param sysPermission
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:del')")
    public Results deletePermission(SysPermission sysPermission) {
        return permissionService.delete(sysPermission.getId());
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseBody
    public Results getMenu(Long userId) {
        return permissionService.getMenu(userId);
    }
}
