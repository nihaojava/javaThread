package chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.lang.System.out;

/**
 * Created by zhangzhao on 2018/8/26.
 *
 * UnmodifiableList 对传入的List的进行了包装，add等方法进行了重写，之间返回Exception；
 * 其中的list 指向传入的list，（随指向的list变化而变化）
 */
public class unmodifiableListTest {
    public static void main(String[] args){
        List a = new ArrayList<String>();
        a.add("a");
        a.add("b");
        a.add("c");

        List b = Collections.unmodifiableList(a);
       // b.add("x");

        b.forEach(out::println);

        a.add("x");

        b.forEach(out::println);

    }
}
