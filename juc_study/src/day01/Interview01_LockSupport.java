package day01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 实现一个容器,提供两个方法,add,size,写两个线程,线程1添加10个元素到容器, 线程2实现监控元素的个数,当个数到5个时,线程2给出提示并结束
 *
 * LockSupport解决
 */
public class Interview01_LockSupport {
    static List<Integer> list = new ArrayList<>();

    public void add(Integer i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    static Thread t1;
    static Thread t2;

    public static void main(String[] args) {
        Interview01_LockSupport interview = new Interview01_LockSupport();

        t1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                interview.add(i);
                System.out.println(i);
                if (interview.size()==5){
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        },"t1");

        t2 = new Thread(()->{
            LockSupport.park();
            System.out.println("t2 结束");
            LockSupport.unpark(t1);
        },"t2");
        t1.start();
        t2.start();
    }
}
