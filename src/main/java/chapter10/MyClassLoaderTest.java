package chapter10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by zhangzhao on 2018/8/27.
 */
public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException,IllegalAccessError,InstantiationError,NoSuchMethodException,
            InvocationTargetException{

        //声明一个myclassLoader
        MyClassLoader classLoader = new MyClassLoader();
        //使用MyClassLoader加载HelloWorld
        Class<?> aClass = classLoader.loadClass("com.chapter10.HelloWorld");

        System.out.println(aClass.getClassLoader());
        //
        Object helloWorld = null;
        try {
            helloWorld = aClass.newInstance();

        System.out.println(helloWorld);

        Method welcomeMethod = aClass.getMethod("welcome");
        String result = (String)welcomeMethod.invoke(helloWorld);
        System.out.println("Result: "+result);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
