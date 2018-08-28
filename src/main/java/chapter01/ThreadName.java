package chapter01;

import java.util.stream.IntStream;

/**
 * Created by zhangzhao on 2018/8/22.
 */
public class ThreadName {
    public static void main (String[] args){
        // jdk8 的stream 和 lambda 特性
        // 调用 thread 的无参构造方法，new了5个thread并启动，执行
        IntStream.range(0,5).boxed().map(i-> new Thread(() -> System.out.println(Thread.currentThread().getName())))
                .forEach(Thread::start);
    }
}
