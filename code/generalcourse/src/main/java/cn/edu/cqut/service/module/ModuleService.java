package cn.edu.cqut.service.module;

import cn.edu.cqut.dao.BaseDao;
import cn.edu.cqut.dao.ModuleDao;
import cn.edu.cqut.pojo.SubModule;
import cn.edu.cqut.util.DBUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
