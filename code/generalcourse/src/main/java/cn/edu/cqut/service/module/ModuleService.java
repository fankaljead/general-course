package cn.edu.cqut.service.module;

import cn.edu.cqut.dao.BaseDao;
import cn.edu.cqut.dao.EntityDao;
import cn.edu.cqut.dao.ModuleDao;
import cn.edu.cqut.pojo.SubModule;
import cn.edu.cqut.util.DBUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 17:16
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 对这个文件的描述
 **/
public class ModuleService implements IModuleService{
    private ModuleDao dao = new ModuleDao();

    public JSONArray getModule(Integer account) {
        // 返回的 json 数组
        JSONArray jsonArray = dao.getModule(account);

        return jsonArray;
    }

    /**
     * 更新子模块
     * @param subModule
     * @return Result.SUCCESS 成功 Result.FAILED 失败
     */
    public Integer updateModule(SubModule subModule) {
        return BaseDao.update(subModule);
    }

    /**
     * 获取所有子模块
     * @return
     */
    public List<SubModule> getAllSubmodules() {
        List<SubModule> subModules = new ArrayList<SubModule>();

        String sql = dao.getAllSubmodules();

        try {
            ResultSet set = DBUtil.query(sql);

            while (set.next()) {
                SubModule subModule = new SubModule();
                subModule.setId(set.getInt("id"));
                subModule.setParentId(set.getInt("parentModuleId"));
                subModule.setName(set.getString("name"));
                subModule.setStatus(set.getInt("status"));

                subModules.add(subModule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return subModules;
    }
}
