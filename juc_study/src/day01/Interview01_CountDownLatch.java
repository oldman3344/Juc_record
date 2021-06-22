package day01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 实现一个容器,提供两个方法,add,size,写两个线程,线程1添加10个元素到容器, 线程2实现监控元素的个数,当个数到5个时,线程2给出提示并结束
 *
 * CountDownLatch解决
 */
public class Interview01_CountDownLatch {
    static List<Integer> list = new ArrayList<>();

    public void add(Integer i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        Interview01_CountDownLatch interview = new Interview01_CountDownLatch();
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                interview.add(i);
                System.out.println(i);
                if (interview.size()==5){
                    try {
                        latch2.countDown();
                        latch1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t1");

        Thread t2 = new Thread(()->{
            //if (interview.size()!=5){
                try {
                    latch2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
           //}
            System.out.println("t2 结束");
            latch1.countDown();
        },"t2");
        t1.start();
        t2.start();
    }
}
