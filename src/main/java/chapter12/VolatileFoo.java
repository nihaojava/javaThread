package chapter12;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangzhao on 2018/8/28.
 */
// 猜测使用①或②分别的输出结果
public class VolatileFoo {
    //init_value 的最大值
    final static int MAX = 5;
    //init_value的初始值
    //static int init_value=0; //①
    static volatile int init_value=0; //②
    public static void main(String[] args){
        //启动一个Reader线程，当发现local_value 和 init_value不同时，则输出init_value 被修改的信息
        new Thread(()->{
            int localValue = init_value;
            while (localValue<MAX){
                if(init_value != localValue){
                    System.out.printf("The init_value is updated to [%d]\n",init_value);
                    //对localValue进行重新赋值
                    localValue = init_value;
                }
            }
        },"Reader").start();

        //启动Updater线程，主要用于对init_value的修改，当local_value>=5的时候退出生命周期

        new Thread(()->{
            int localValue = init_value;
            while (localValue < MAX){
                //修改init_value
                System.out.printf("The init_value will be changed to [%d]\n", ++localValue);
                init_value = localValue;

                //短暂休眠，目的是为了使Reader线程能够来得及输出变化内容
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"update").start();
    }
}
