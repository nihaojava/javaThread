package chapter01;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangzhao on 2018/8/22.
 */
public class ThreadInterrupt {
        public static void main(String[] args) throws InterruptedException{
            Thread thread = new Thread(()->{
                try{
                    TimeUnit.MINUTES.sleep(1);
                }catch (InterruptedException e){
                    System.out.print("Oh,i am be interrupted.");
                }
            });
            thread.start();
            TimeUnit.MILLISECONDS.sleep(5);
            thread.interrupt();



        }
}
