package day01;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法，能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * Synchronized解决
 */
public class Interview02_Synchronized<T> {
    private final LinkedList<T> LIST = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    public synchronized void put(T t){
        while (LIST.size()==MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LIST.add(t);
        ++count;
        this.notifyAll();
    }

    public synchronized T get(){
        while (LIST.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = LIST.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        Interview02_Synchronized<String> interview = new Interview02_Synchronized<>();

        //消费者
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(interview.get());
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //提供者
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    interview.put(Thread.currentThread().getName()+",包子"+j);
                }
            },"蒸笼"+i+"号").start();
        }
    }
}
