package chapter01;

import sun.security.krb5.internal.Ticket;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangzhao on 2018/8/22.
 */
public class TicketWindowRunnable implements Runnable {
    private int index =1;
    private final static int MAX = 50;
    private final static Object MUTEX = new Object();
    @Override
    public void run(){
        synchronized (MUTEX){
            while (index<=MAX){
                //index++;
                try{
                    TimeUnit.MILLISECONDS.sleep(2);     // sleep 方法不会释放monitor锁,wait 会释放
                }catch (InterruptedException e){
                    System.out.print("Oh,i am be interrupted.");
                }
                System.out.println(Thread.currentThread()+"的号码是："+index++);
            }
        }
    }

    public static void main(String[] args){
        final TicketWindowRunnable task = new TicketWindowRunnable();
        Thread windowThread1 = new Thread(task,"1号");
        Thread windowThread2 = new Thread(task,"2号");
        Thread windowThread3 = new Thread(task,"3号");


        windowThread1.start();
        windowThread2.start();
        windowThread3.start();

    }
}
