package chapter05;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.concurrent.ThreadLocalRandom.current;
/**
 * Created by zhangzhao on 2018/8/26.
 */
// 多个线程通过lock() 方法争抢锁
public class BooleanLockTest1 {
    //定义BooleanLock
    private final Lock lock = new BooleanLock();
    //使用try..finally 语句块确保lock每次都能被正确释放
    public void syncMethod() {
        //加锁
        try {
            lock.lock();
            int randomInt = current().nextInt(10);
            System.out.print(Thread.currentThread() + " get the lock. ");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }
        public static void main(String[] args){
        BooleanLockTest1 blt = new BooleanLockTest1();
        //定义一个线程并且启动
        IntStream.range(0,10).mapToObj(i->new Thread(blt::syncMethod)).forEach(Thread::start);
    }
}
