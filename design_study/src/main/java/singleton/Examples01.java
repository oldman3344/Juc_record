package singleton;

/**
 * 单例模式（饿汉式）
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用
 * 缺点：不管用到与否，类加载时就会完成实例化
 * Class.forName("类名")只把class Load到内存但不实例化
 * @author oldman
 * @date 2021/11/11 21:54
 */
public class Examples01 {
    //静态变量load到内存就会初始化
    private static final Examples01 INSTANCE = new Examples01();

    private Examples01(){}

    public void m(){
        System.out.println("m");
    }

    public static Examples01 getInstance(){
        return INSTANCE;
    }
}
