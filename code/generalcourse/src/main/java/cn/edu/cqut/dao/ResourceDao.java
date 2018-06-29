package cn.edu.cqut.dao;

import cn.edu.cqut.pojo.Article;
import cn.edu.cqut.util.ObjectToSql;

import java.lang.reflect.InvocationTargetException;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 20:48
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 资源操作 包括文章和文件 每个方法返回 sql语句
 **/
public class ResourceDao {
    // 默认页面
    public static final Integer DEFAULT_PAGE_INDEX = 1;
    // 默认页面大小
    public static final Integer DEFAULT_PAGE_SIZE = 7;

    /**
     * 新增资源 文章
     * @param article
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String addResource(Article article) throws InvocationTargetException, IllegalAccessException {
        return ObjectToSql.getSave(article);
    }

    /**
     * 获取资源 包括文章和文件等资源
     * @param columnId 栏目id
     * @param title 资源名字
     * @param startTime 创建时间的范围开始
     * @param endTime 创建时间的范围结束
     * @param pageIndex 当前页
     * @param pageSize 每页接收的大小
     * @return
     */
    public String getResources(Integer columnId, String title, String startTime,
                               String endTime, Integer pageIndex, Integer pageSize) {
        StringBuffer sql = new StringBuffer();

        // 从 article file colunm 三张表里查询，并且使用左外连接
        sql.append("select * from article left outer join file on (article.id=file.articleId), colunm ");

        sql.append(" where article.columnId=colunm.id ");


        // 当栏目id不为空时，添加栏目的查询条件
        if (!(columnId == null || columnId == 0)) {
            sql.append(" and columnId=" + columnId);
        }

        // 筛选是否进行标题搜索
        if (!(title == null || title.equals("") || title.equals("undefined"))) {
            sql.append(" and title='" + title + "'");
        }

        // 判断是否进行时间开始搜索
        if (!(startTime == null || startTime.equals("") || startTime.equals("undefined"))) {
            sql.append(" and createTime between " + startTime);
        }

        // 判断是否进行时间最后范围搜索
        if (!(endTime == null || endTime.equals("") || endTime.equals("undefined"))) {
            sql.append(endTime);
        }


        // 分页
        if (pageIndex == null || pageIndex == 0) {
            pageIndex = ResourceDao.DEFAULT_PAGE_INDEX;
        }

        if (pageSize == null || pageSize == 0) {
            pageSize = ResourceDao.DEFAULT_PAGE_SIZE;
        }

        // 获取的第一条记录的下标
        Integer start = (pageIndex - 1) * pageSize + 1;
        sql.append(" limit " + start + "," + pageSize);

        System.out.println("sql:" + sql);

        return sql.toString();
    }
}
