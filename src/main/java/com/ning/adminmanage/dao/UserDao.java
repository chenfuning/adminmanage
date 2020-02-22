package com.ning.adminmanage.dao;

import com.ning.adminmanage.dto.UserDto;
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
    @Select("select *from sys_user t where t.telephone=#{telephone}")
    SysUser getUserByPage(String phone);

    @Select("select *from sys_user t where t.id=#{id}")
    SysUser getUserById(Long id);

    int  updateUser(UserDto userDto);
    @Delete("delete from sys_user where id = #{id}")
    int deleteUser(int id);
    /******************模糊查询 start*******************************************/
    /**
     * 查询该模糊匹配到的用户数量
     * @param username
     * @return
     */
    @Select("select count(*) from sys_user t where t.username like '%${username}%'")
    Long getUserByFuzzyUsername(@Param("username")String username);

    /**
     * 查询该模糊匹配到的具体数据
     * @param username
     * @param offset
     * @param limit
     * @return
     */
    @Select("select * from sys_user t where t.username like '%${username}%'  limit #{startPosition},#{limit}")
    List<SysUser> getUserByFuzzyUsernameByPage(@Param("username")String username,@Param("startPosition")Integer offset, @Param("limit")Integer limit);

    @Update("update sys_user t set t.password = #{password} where t.id = #{id}")
    int changePassword(@Param("id")Long id, @Param("password")String password);
    /******************模糊查询 end*******************************************/
}
