package strategy;

/**
 * @author oldman
 * @date 2021/11/13 15:36
 */
public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setName("小红");
        CatAction catAction = new CatAction();
        catAction.speak(cat);

        Dog dog = new Dog();
        dog.setName("小黑");
        DogAction dogAction = new DogAction();
        dogAction.speak(dog);
    }
}
