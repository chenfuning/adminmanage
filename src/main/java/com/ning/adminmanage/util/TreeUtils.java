package com.ning.adminmanage.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ning.adminmanage.model.SysPermission;

import java.util.List;


public class TreeUtils {

    /**
     * 菜单树
     *创建一个有数据的JSONarray的对象
     * @param parentId 父id，根据这个判断是不是目录
     * @param permissionsAll 全部数据
     * @param array fastJSON容器
     */
    public static void setPermissionsTree(Integer parentId, List<SysPermission> permissionsAll, JSONArray array) {
        //遍历全部数据permissionsAll
        for (SysPermission per : permissionsAll) {
            //如果它的parentId匹配
            if (per.getParentId().equals(parentId)) {
                //数据SysPermission转化成JsonObject对象，存入array中去
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                array.add(parent);
                /**判断是否还有子节点
                判断条件里面是java8的新特性，stream（）是循环遍历，filter筛选per.id在数据中有多少个是和他相同的，如果还有就再做递归操作
                 **/
                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    //添加子目录
                    JSONArray child = new JSONArray();
                    parent.put("child", child);
                    setPermissionsTree(per.getId(), permissionsAll, child);
                }
            }
        }
    }
}
