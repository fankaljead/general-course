package cn.edu.cqut;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author 周翔辉
 * @create: 2018年06月30日
 * @time: 18:50
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 对这个文件的描述
 **/
public class JSArrayTest {
    @Test
    public void testJSArray() {
        String ids = "[{id: 1},{id: 0},{id: 2}]";

        JSONArray array = JSONArray.parseArray(ids);

        for (int i = 0; i < array.size(); i++) {
            System.out.println("id: " + JSONObject.parseObject(array.getString(i)).get("id"));
        }
    }

    /**
     * 测试[10000, 10001, ...]数组
     */
    @Test
    public void testArray() {
        String ids = "[100000, 100001, 100002]";
        JSONArray array = JSONArray.parseArray(ids);

        for (int i = 0; i < array.size(); i++) {
            System.out.println("id: " + (array.getString(i)));
        }
    }
}
