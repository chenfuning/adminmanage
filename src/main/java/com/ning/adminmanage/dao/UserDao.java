package com.ning.adminmanage.dao;

import com.ning.adminmanage.model.SysUser;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserDao {
    /**
     * 查询用户
     * @param username
     * @return
     */
	@Select("select * from sys_user t where t.username = #{username}")
	SysUser getUser(String username);
    //-------------------------------------用户分页dao start--------------------------------------------
    /**
     * 查询用户总页数
     * @return
     */
    @Select("select count(*) from sys_user t")
	Long countAllUsers();

    /**
     * 返回查询到的用户list
     * @return
     */
    @Select("select * from sys_user t order by t.id limit #{startPosition},#{limit}")
    List<SysUser> getAllUserByPage(@Param("startPosition")Integer startPosition,@Param("limit")Integer limit);
    //-------------------------------------用户分页dao end--------------------------------------------
    //用户新增，注这里直接参数是user
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_user(username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, updateTime) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
    int save(SysUser user);
}
