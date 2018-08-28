package chapter01;

/**
 * Created by zhangzhao on 2018/8/22.
 */
// 模板模式， TemplateMethod 的 class 里面定义模板方法wrapPrint，实现类完成具体实现
// Thread class 中也用到了模板模式，Thread中的
// start方法 类似  TemplateMethod的print;
// run方法 类似 TemplateMethod的wrapPrint;
public class TemplateMethod {
    // final 不允许修改
    public final void print(String message){
        System.out.println("#############");
        wrapPrint(message);
        System.out.println("#############");
    }

    protected  void  wrapPrint(String message){

    }

    public static void main(String[] args){
        // 这种写法，new 一个class，里面直接重写method
        TemplateMethod t1 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String message){
                System.out.print("*"+message+"*");
            }
        };
        t1.print("Hello Thread");

        TemplateMethod t2 = new TemplateMethod(){
            @Override
            protected void  wrapPrint(String message){
                System.out.print("#"+message+"#");
            }
        };
        t2.print("Hello Thread");
    }
}
