package singleton;

/**
 * 单例模式（懒汉式）
 * 加锁synchronized
 * @author oldman
 * @date 2021/11/11 22:37
 */
public class Examples04 {
    private static Examples04 INSTANCE;

    private Examples04(){}

    //通过减小同步代码块来提高效率
    public static Examples04 getInstance(){
        if (null==INSTANCE){
            //双重检查
            synchronized(Examples04.class){
                if (null==INSTANCE){
                    INSTANCE = new Examples04();
                }
            }
        }
        return INSTANCE;
    }
}
