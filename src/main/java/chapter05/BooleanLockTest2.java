package chapter05;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangzhao on 2018/8/26.
 */
// 可中断被阻塞的线程
public class BooleanLockTest2 {
    public static void main(String[] args){
        BooleanLockTest1 blt = new BooleanLockTest1();
        new Thread(blt::syncMethod,"T1").start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(blt::syncMethod,"T2");
        t2.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();

    }
}
