package cn.edu.cqut.dao;

import cn.edu.cqut.util.DBUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 17:17
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 数据库操作 模块 module
 **/
public class ModuleDao {
    /**
     * 获取模块 通过账号 account
     * @param account
     * @return 模块数组
     */
    public JSONArray getModule(Integer account) {
        JSONArray jsonArray = new JSONArray();

        try {
            // 查询 account 的角色 id
            ResultSet roleSet = DBUtil.query("select * from employee " +
                    "where account=" + account);


            Integer roleId = 0;

            if (roleSet.next()) {
                roleId = roleSet.getInt("roleId");
            }

            // 查询所有的父模块
            ResultSet upSet = DBUtil.query("select * from module");

            while (upSet.next()) {
                JSONObject upModule = new JSONObject();
                jsonArray.add(upModule);

                Integer moduleId = upSet.getInt("id");
                upModule.put("moduleId", moduleId);
                upModule.put("moduleName", upSet.getString("name"));
                upModule.put("status", upSet.getInt("status"));

                JSONArray subModules = new JSONArray();
                upModule.put("subModules", subModules);

                // 查询所有的子模块
                ResultSet subSet = DBUtil.query("select * from permission, submodule where " +
                        "permission.roleId=" + roleId +
                        " and permission.moduleId=submodule.id and submodule.parentModuleId="+ moduleId);

                while (subSet.next()) {
                    JSONObject subModule = new JSONObject();
                    subModule.put("subModuleId", subSet.getInt("submodule.id"));
                    subModule.put("subModuleName", subSet.getString("submodule.name"));
                    subModule.put("status", subSet.getInt("status"));
                    subModule.put("parentId", subSet.getInt("parentModuleId"));

                    subModules.add(subModule);
                }
            }

            return jsonArray;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    /**
     * 获取所有子模块
     * @return
     */
    public String getAllSubmodules() {
        StringBuffer sql = new StringBuffer();

        sql.append("select * from submodule");

        return  sql.toString();
    }
}
