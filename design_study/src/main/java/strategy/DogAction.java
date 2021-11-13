package strategy;

/**
 * @author oldman
 * @date 2021/11/13 20:54
 */
public class DogAction implements Action<Dog>{
    @Override
    public void speak(Dog dog) {
        System.out.println(String.format("小狗，%s在叫",dog.getName()));
    }
}
