package com.ning.adminmanage.controller;

import com.ning.adminmanage.base.result.PageTableRequest;
import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.dao.RoleDao;
import com.ning.adminmanage.dto.RoleDto;
import com.ning.adminmanage.model.SysRole;
import com.ning.adminmanage.model.SysUser;
import com.ning.adminmanage.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 分页查询Role
     * @param pageTableRequest
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:query')")
    public Results<SysRole> getRoles(PageTableRequest pageTableRequest){
        pageTableRequest.countOffset();
        return roleService.getAllRolesByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
    }

    @GetMapping("/findRoleByFuzzyRoleName")
    @ResponseBody
    public Results<SysRole> findRoleByFuzzyRoleName(PageTableRequest pageTableRequest, String rolename){
        log.info("rolename="+rolename);
        pageTableRequest.countOffset();
        return roleService.getRolesByFuzzyRoleName(rolename,pageTableRequest.getOffset(),pageTableRequest.getLimit());
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('sys:role:add')")
    public String addRole(Model model){
        model.addAttribute(new SysRole());
        return "role/role-add";
    }
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:add')")
    public Results saveRole(@RequestBody RoleDto roleDto){
        return roleService.save(roleDto);
    }

    @GetMapping("/edit")
    public String editRole(Model model, SysRole role) {
        model.addAttribute("sysRole",roleService.getRoleById(role.getId()));
        return "role/role-edit";
    }
    @PostMapping(value = "/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:edit')")
    public Results updateRole(@RequestBody RoleDto roleDto) {
        return roleService.update(roleDto);
    }

    @GetMapping(value = "/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:del')")
    public Results<SysRole> deleteRole(RoleDto roleDto){
        return roleService.delete(roleDto.getId());
    }
}
