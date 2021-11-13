package strategy;

/**
 * @author oldman
 * @date 2021/11/13 20:51
 */
public class CatAction implements Action<Cat>{
    @Override
    public void speak(Cat cat) {
        System.out.println(String.format("小猫，%s在叫",cat.getName()));
    }
}
