package chapter09;

/**
 * Created by zhangzhao on 2018/8/27.
 */
// 思考输出结果，以及原因
// 考察的是 类加载过程
// 连接-准备阶段 赋类型的初始值   x=0,y=0;
// 初始化阶段， 执行完 newSingleton  ， x=1,y=1;  然后继续执行，x=0
    // 最终结果 x=0,y=1
public class Singleton {
    private static Singleton instance = new Singleton();
    private static int x = 0;
    private static int y;

    
    private Singleton(){
        x++;
        y++;
    }
    public static Singleton getInstance(){
        return instance;
    }
    public static void main(String[] args){
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
