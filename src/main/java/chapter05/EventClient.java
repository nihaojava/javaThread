package chapter05;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangzhao on 2018/8/23.
 */
public class EventClient {
    public static void main(String[] args){
        final EventQueue eventQueue = new EventQueue();
        new Thread(()->
            {
                for(;;){
                    eventQueue.offer(new EventQueue.Event());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        ,"Producer").start();

        new Thread(()->
            {
                for(;;){
                    eventQueue.take();
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        ,"Consumer").start();
    }
}
