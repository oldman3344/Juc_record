package singleton;

/**
 * 单例模式（静态内部类）
 * JVM保证单例（虚拟机加载一个class只加载一次）
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 * @author oldman
 * @date 2021/11/11 22:44
 */
public class Examples05 {
    private Examples05(){}

    //类的静态内部类，在类加载的时候，内部类不会被加载（虚拟机加载一个class只加载一次），所以线程安全
    private static class Examples05Holder{
        private static final Examples05 INSTANCE = new Examples05();
    }

    public static Examples05 getInstance(){
        return Examples05Holder.INSTANCE;
    }
}
