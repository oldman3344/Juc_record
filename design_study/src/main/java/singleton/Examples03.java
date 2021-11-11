package singleton;

/**
 * 单例模式（懒汉式）
 * 加锁synchronized，但是效率会下降
 * @author oldman
 * @date 2021/11/11 22:34
 */
public class Examples03 {
    private static Examples03 INSTANCE;

    private Examples03(){}

    //static synchronized class锁
    public static synchronized Examples03 getInstance(){
        if (null==INSTANCE){
            INSTANCE = new Examples03();
        }
        return INSTANCE;
    }
}
