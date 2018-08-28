package chapter01;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangzhao on 2018/8/23.
 */

// 使用synchronized关键字同步类的不同的实例方法，争抢的是同一个monitor lock
// this monitor
public class ClassMonitor {
    public static synchronized void method1(){
        System.out.println(Thread.currentThread().getName() + " enter to method1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void method2(){
        System.out.println(Thread.currentThread().getName() + " enter to method2");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void method3(){
        synchronized(this) {    // 此时 和 在方法前加synchronized 效果一样
            System.out.println(Thread.currentThread().getName() + " enter to method3");
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        ClassMonitor thisMonitor = new ClassMonitor();
        new Thread(ClassMonitor::method1,"T1").start();
        new Thread(ClassMonitor::method2,"T2").start();
        new Thread(thisMonitor::method3,"T3").start();
    }
}
