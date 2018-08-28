package chapter05;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhangzhao on 2018/8/26.
 */
public interface Lock {
    void lock() throws InterruptedException;
    void lock(long mills) throws InterruptedException,TimeoutException;
    void unlock();
    List<Thread> getBlockedThreads();
}
