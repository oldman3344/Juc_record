package singleton;

/**
 * 单例模式（懒汉式）
 * 按需初始化，但是会有线程不安全问题
 * @author oldman
 * @date 2021/11/11 22:22
 */
public class Examples02 {
    private static Examples02 INSTANCE;

    private Examples02(){}

    public static Examples02 getInstance(){
        //第一个线程进入，还没执行new，第二个也验证进入，new两次导致不是同一个实例
        if (null==INSTANCE){
            INSTANCE = new Examples02();
        }
        return INSTANCE;
    }
}
