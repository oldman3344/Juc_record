package singleton;

/**
 * @author oldman
 * @date 2021/11/11 21:55
 */
public class Main {
    public static void main(String[] args) {
        /*Examples01 examples01 = Examples01.getInstance();
        Examples01 examples02 = Examples01.getInstance();
        System.out.println(examples01==examples02); //true*/

        /*Examples02 examples02 = Examples02.getInstance();
        Examples02 examples03 = Examples02.getInstance();
        System.out.println(examples02==examples03);
        for (int i = 0; i < 100; i++) { //懒汉式线程不安全
            new Thread(()->{
                System.out.println(Examples02.getInstance().hashCode());
            }).start();
        }*/

        /*for (int i = 0; i < 100; i++) { //线程安全
            new Thread(()->{
                System.out.println(Examples03.getInstance().hashCode());
            }).start();
        }*/

        /*for (int i = 0; i < 100; i++) { //减小同步代码块提高效率，双重检查，线程安全，否则线程不安全
            new Thread(()->{
                System.out.println(Examples04.getInstance().hashCode());
            }).start();
        }*/

        /*Examples05 examples05 = Examples05.getInstance();
        Examples05 examples06 = Examples05.getInstance();
        System.out.println(examples05==examples06); //true*/

        /*for (int i = 0; i < 100; i++) { //枚举单例，不仅线程同步，而且防止反序列化
            new Thread(()->{
                System.out.println(Examples06.INSTANCE.hashCode());
            }).start();
        }*/
    }
}
