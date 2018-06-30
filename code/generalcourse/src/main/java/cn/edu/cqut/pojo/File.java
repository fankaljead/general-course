package cn.edu.cqut.pojo;

import cn.edu.cqut.util.Column;
import cn.edu.cqut.util.Table;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:31
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 文件资源
 **/
@Table(name = "file", caption = "file")
public class File extends Entity {

    @Column(type = "int", isId = true, name = "id", caption = "fileId")
    private Integer id;// 文件id

    @Column(type = "int", name = "articleId", caption = "articleId")
    private Integer articleId;// 对应文章的id

    @Column(name = "path", caption = "path")
    private String path;// 文件存放路径Article

    @Column(name = "name", caption = "fileName")
    private String name;

    public File() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
