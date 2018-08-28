package chapter01;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangzhao on 2018/8/21.
 */
public class TryConcurrency {
    public static void main(String[] args){
        // 通过匿名内部类的方式创建线程，并且重写其中的run()方法
        new Thread() {
            public void run() {
                browseNews();
            }
        }.start();

        //enjoyMusic();
        new Thread(TryConcurrency::enjoyMusic).start();
    }

    /*
    *  Browse the latest news
    * */
    private static void browseNews(){
        for(;;){
            System.out.println(" Uh-huh,the good news;");
            sleep(1);
        }
    }

    /* listening and enjoy the music
    * */

    private static void  enjoyMusic(){
        for(;;){
            System.out.println("the music !");
            sleep(1);
        }
    }

    private static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 }
