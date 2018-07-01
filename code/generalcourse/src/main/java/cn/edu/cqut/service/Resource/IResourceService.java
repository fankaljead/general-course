package cn.edu.cqut.service.Resource;

import cn.edu.cqut.pojo.Article;
import cn.edu.cqut.pojo.File;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 20:45
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 资源操作 包括文章和文件
 **/
public interface IResourceService {
    /**
     * 添加资源 文章
     * @param article
     * @return Result.SUCCESS 成功 Result.FAILED 失败
     */
    public Integer addResource(Article article);

    /**
     * 获取资源
     * @param columnId
     * @param title
     * @param startTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public JSONArray getResources(Integer columnId, String title, String startTime,
                                  String endTime, Integer pageIndex, Integer pageSize);

    /**
     * 获取资源详情（既可以获取文章也可以获取文件）
     * @param resourceId 资源 article id
     * @return
     */
    public JSONObject getResourceContent(Integer resourceId);


    /**
     * 全站搜索
     * @param keyWords
     * @return
     */
    public JSONArray search(String keyWords);


    /**
     * 更新文章
     * @param article
     * @return
     */
    public Integer updateResource(Article article);

    /**
     * 上传文件
     * @param file
     * @return Result.SUCCESS 成功 Result.FAILED 失败
     */
    public Integer uploadFile(File file);

    /**
     * 下载文件
     * @param fileId
     * @return Result.SUCCESS 成功 Result.FAILED 失败
     */
    public File downloadFile(Integer fileId);
}
