package cn.edu.cqut.pojo;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:31
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 文件资源
 **/
public class File {

    private Integer id;// 文件id
    private Integer articleId;// 对应文章的id
    private String path;// 文件存放路径

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
}
