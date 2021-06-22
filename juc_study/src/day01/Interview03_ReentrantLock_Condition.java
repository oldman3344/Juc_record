package day01;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用两个线程顺序打印A1B2C3D4..Z26
 *
 * ReentrantLock+Condition解决
 */
public class Interview03_ReentrantLock_Condition {
    private static LinkedList<String> letter = new LinkedList<>();
    private static LinkedList<Integer> num = new LinkedList<>();

    static {
        letter.add("A");
        letter.add("B");
        letter.add("C");
        letter.add("D");
        letter.add("E");
        letter.add("F");
        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);
        num.add(5);
        num.add(6);
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition letterCond = lock.newCondition();
        Condition numCond = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                for (String o : letter) {
                    System.out.print(o);
                    letterCond.await();
                    numCond.signal();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                for (Integer o : num) {
                    System.out.print(o);
                    letterCond.signal();
                    numCond.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();
    }
}
