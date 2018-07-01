package cn.edu.cqut;

import org.junit.Test;

/**
 * @author 周翔辉
 * @create: 2018年07月01日
 * @time: 20:05
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 对这个文件的描述
 **/
public class FileDirTest {
    @Test
    public void fileDir() {
        String s = "C:\\Users\\72867\\Desktop\\general-course\\code\\generalcourse\\\\out\\artifacts\\generalcourse_war_exploded\\";
        System.out.println(s);
        System.out.println(s.replaceAll("\\\\", "/"));
        s = s.replaceAll("\\\\", "/");
        System.out.println(s.replaceAll("//", "/"));
    }
}
