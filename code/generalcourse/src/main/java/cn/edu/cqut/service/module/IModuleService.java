package cn.edu.cqut.service.module;

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
}
