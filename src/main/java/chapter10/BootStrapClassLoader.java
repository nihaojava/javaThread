package chapter10;


/**
 * Created by zhangzhao on 2018/8/27.
 */
public class BootStrapClassLoader {
    public static void main(String[] args){
        System.out.println("Bootstrap:" + String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
        // ext classLoader load path
        System.out.println(System.getProperty("java.ext.dirs"));

        // applicationClassLoader load path
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(BootStrapClassLoader.class.getClassLoader());
    }
}
