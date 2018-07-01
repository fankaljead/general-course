package cn.edu.cqut;

import org.junit.Test;

/**
 * @author 周翔辉
 * @create: 2018年07月01日
 * @time: 17:07
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 测试md5加密
 **/
public class MD5Test {
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
    @Test
    public void testMD5() {
        String password = "123456";
        String pm = MD5(MD5Test.MD5(password));

        System.out.println(pm.equals("e10adc3949ba59abbe56e057f20f883e"));
    }
}
