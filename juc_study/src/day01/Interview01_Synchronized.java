package day01;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个容器,提供两个方法,add,size,写两个线程,线程1添加10个元素到容器, 线程2实现监控元素的个数,当个数到5个时,线程2给出提示并结束
 *
 * Synchronized解决
 */
public class Interview01_Synchronized {
    static List<Integer> list = new ArrayList<>();

    public void add(Integer i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        Interview01_Synchronized interview = new Interview01_Synchronized();
        Object o = new Object();

        Thread t1 = new Thread(()->{
            try {
                synchronized (o){
                    for (int i = 0; i < 10; i++) {
                        interview.add(i);
                        System.out.println(i);
                        if (interview.size()==5){
                            o.notify();
                            o.wait();
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()->{
            try {
                synchronized (o){
                    if (interview.size()!=5){
                        o.wait();
                    }
                    System.out.println("t2 结束");
                    o.notify();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }
}
