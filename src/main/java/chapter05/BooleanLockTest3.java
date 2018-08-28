package chapter05;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.ThreadLocalRandom.current;
/**
 * Created by zhangzhao on 2018/8/26.
 */
// 阻塞的线程可超时（）
public class BooleanLockTest3 {
    //定义BooleanLock
    private final Lock lock = new BooleanLock();

    public void syncMethodTimeoutable() {
        //加锁
        try {
            lock.lock(3000);
            int randomInt = current().nextInt(10);
            System.out.print(Thread.currentThread() + " get the lock. ");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException | TimeoutException e) { // 这种写法
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args){
        BooleanLockTest3 blt = new BooleanLockTest3();

        new Thread(blt::syncMethodTimeoutable,"T1").start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(blt::syncMethodTimeoutable,"T2");
        t2.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
