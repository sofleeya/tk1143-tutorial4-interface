// Fruit.java
public class Fruit implements Edible {
    protected String name;

    public Fruit(String name) {
        this.name = name;
    }

    @Override
    public String howToEat() {
        return name + ". " + name + " can be eaten fresh.";
    }
}
