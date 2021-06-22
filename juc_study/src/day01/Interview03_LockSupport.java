package day01;

import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;

/**
 * 用两个线程顺序打印A1B2C3D4..Z26
 *
 * LockSupport解决
 */
public class Interview03_LockSupport {
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

    static Thread t1;
    static Thread t2;

    public static void main(String[] args) {
        t1 =new Thread(() -> {
            for (String s : letter) {
                System.out.print(s);
                LockSupport.park();
                LockSupport.unpark(t2);
            }

        });

        t2 = new Thread(() -> {
            for (Integer o : num) {
                System.out.print(o);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        });
        t1.start();
        t2.start();
    }
}
