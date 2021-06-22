package day01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 实现一个容器,提供两个方法,add,size,写两个线程,线程1添加10个元素到容器, 线程2实现监控元素的个数,当个数到5个时,线程2给出提示并结束
 *
 * Semaphore解决
 */
public class Interview01_Semaphore {
    static List<Integer> list = new ArrayList<>();

    public void add(Integer i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }
    static Thread t1;
    static Thread t2;

    public static void main(String[] args) {
        Interview01_Semaphore interview = new Interview01_Semaphore();
        Semaphore s = new Semaphore(1);

        t1 = new Thread(() -> {
            try {
                s.acquire();
                for (int i = 0; i < 10; i++) {
                    interview.add(i);
                    System.out.println(i);
                    if (interview.size()==5){
                        s.release();
                        t2.join();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t2 = new Thread(()->{
            try {
                s.acquire();
                System.out.println("t2 结束");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
