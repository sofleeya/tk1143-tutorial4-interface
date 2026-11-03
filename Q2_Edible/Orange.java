// Orange.java
public class Orange extends Fruit {

    public Orange(String name) {
        super(name);
    }

    @Override
    public String howToEat() {
        return name + ". " + name + " can make orange butter cake.";
    }
}
