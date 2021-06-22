package day01;

import java.util.LinkedList;

/**
 * 用两个线程顺序打印A1B2C3D4..Z26
 *
 * Synchronized解决
 */
public class Interview03_Synchronized {
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
        Object lock = new Object();
        new Thread(()->{
            synchronized (lock){

                letter.forEach(o->{
                    System.out.print(o);
                    try {
                        lock.wait();
                        lock.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }).start();

        new Thread(()->{
           synchronized (lock){
               num.forEach(o->{
                   System.out.print(o);
                   try {
                       lock.notify();
                       lock.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               });
           }
        }).start();
    }
}
