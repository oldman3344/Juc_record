package day01;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法，能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * ReentrantLock解决
 */
public class Interview02_ReentrantLock<T> {
    private final LinkedList<T> LIST = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition p = lock.newCondition();
    private Condition c = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while (LIST.size() == MAX) {
                p.await();
            }
            LIST.add(t);
            ++count;
            c.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (LIST.size() == 0) {
                c.await();
            }
            t = LIST.removeFirst();
            count--;
            p.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        Interview02_ReentrantLock<String> interview = new Interview02_ReentrantLock<>();

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
                    System.out.println(Thread.currentThread().getName()+",包子"+j);
                }
            },"蒸笼"+i+"号").start();
        }
    }
}
