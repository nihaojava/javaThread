package chapter09;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by zhangzhao on 2018/8/27.
 */
public class ClassInit {
    // static 代码块和 static变量赋值 在类加载的初始化阶段完成，
    // 一个 class 只会被同一个 classLoader 加载一次，所以只会执行一次
    static {
        System.out.print("The ClassInit static code block will be invoke.");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        IntStream.range(0,5).forEach(i->new Thread(ClassInit::new));
    }
}
