package chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread; // 引入了static 方法，直接可以在下面代码中使用

/**
 * Created by zhangzhao on 2018/8/26.
 */
public class BooleanLock implements Lock{
    private Thread currentThread;
    private boolean locked = false;
    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while (locked){
                final Thread tempThread = currentThread();

                try {
                    if(!blockedList.contains(currentThread))
                        blockedList.add(currentThread());
                    this.wait();
                } catch (InterruptedException e) {
                    //如果当前线程在wait时被中断，则从blockedList中将其移除，避免内存泄露
                    blockedList.remove(tempThread);
                    throw  e;
                } finally {
                }
            }
            blockedList.remove(currentThread());
            this.locked = true;
            currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if(mills<=0){
                this.lock();
            }else{
                long remainingMills = mills;
                long endMills = currentTimeMillis() + mills;
                while (locked){
                    if(remainingMills<=0)
                        throw  new TimeoutException("can not get lock during " + mills + "ms");
                    if (!blockedList.contains(currentThread()))
                        blockedList.add(currentThread());

                    this.wait(remainingMills);
                    remainingMills = endMills - currentTimeMillis();
                }
                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            if(currentThread == currentThread()){
                this.locked = false;
                Optional.of(currentThread().getName()+" release the lock. ").ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList); // 返回只读的list（不可以add等） //https://www.jianshu.com/p/bf2623f18d6a
    }


}

