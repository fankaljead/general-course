package cn.edu.cqut.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 将json字符串转换为hash map<br>
 *
 * @author 周翔辉
 * @create 2018/6/23
 * @time 16:12
 * @project_name questionnaire
 * @email 728678732@qq.com
 */
public class JSONToHashMap<M, V> {
    public static HashMap<Integer, String> parse(String json) {
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        JSONObject jsonObject1 = JSON.parseObject(json);
        Iterator<String> iterator = jsonObject1.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = (String) jsonObject1.get(key);

            hashMap.put(Integer.parseInt(key), value);
        }

        return hashMap;
    }

    public HashMap<M, V> parses(String json) {
        HashMap<M, V> hashMap = new HashMap<M, V>();
        JSONObject jsonObject1 = JSON.parseObject(json);
        Iterator<String> iterator = jsonObject1.keySet().iterator();
        while (iterator.hasNext()) {
            M key = (M) iterator.next();
            V value = (V) jsonObject1.get(key);

            hashMap.put(key, value);
        }

        return hashMap;
    }

}
