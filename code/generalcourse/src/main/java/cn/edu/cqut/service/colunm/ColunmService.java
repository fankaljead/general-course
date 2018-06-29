package cn.edu.cqut.service.colunm;

import cn.edu.cqut.dao.ColunmDao;
import com.alibaba.fastjson.JSONArray;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 19:21
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 数据库操作 colunm 栏目的方法实现
 **/

public class ColunmService implements IColunmService{

    ColunmDao dao = new ColunmDao();

    /**
     * 获取栏目信息
     * @param level 0 表示父栏目 1 表示子栏目
     * @param colunmId 可选
     * @return
     */
    public JSONArray getColunms(Integer level, Integer colunmId) {
        JSONArray objects = dao.getColunm(level, colunmId);

        return objects;
    }
}
