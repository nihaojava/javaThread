package chapter05;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangzhao on 2018/8/23.
 */
// synchronized 的缺点：
// 1.无法控制阻塞时长（无法确定什么时候或去到monitor的lock）
// 2.阻塞不可被中断（比如 阻塞了1个小时候，不想等待了，没办法只能等）
public class SynchronizedDefect {
    public synchronized void syncMethod(){
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SynchronizedDefect defect = new SynchronizedDefect();
        Thread t1 = new Thread(defect::syncMethod,"T1");
        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(defect::syncMethod,"T2");
        t2.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();
        System.out.println(t2.isInterrupted());
        System.out.println(t2.getState());
    }
}
