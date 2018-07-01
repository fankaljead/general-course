package cn.edu.cqut.service.module;

import cn.edu.cqut.pojo.Module;
import cn.edu.cqut.pojo.SubModule;
import com.alibaba.fastjson.JSONArray;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 17:15
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 获取模块
 **/
public interface IModuleService {
    /**
     * 通过账号 account 获取模块信息
     * @param account
     * @return
     */
    public JSONArray getModule(Integer account);


    /**
     * 更新子模块
     * @param subModule
     * @return Result.SUCCESS 成功 Result.FAILED 失败
     */
    public Integer updateModule(SubModule subModule);
}
