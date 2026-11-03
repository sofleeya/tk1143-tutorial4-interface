// Car.java
public class Car implements Price {
    private String brand;
    private double priceCar;
    private Customer buy;
    private String message = "";   // last result, so the GUI can display it

    public Car(String brand, double priceCar) {
        this.brand = brand;
        this.priceCar = priceCar;
    }

    public String getBrand() {
        return brand;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "The car brand is " + brand + " with price RM" + priceCar + ".";
    }

    // if the Customer's salary exceeds RM8000, the Customer buys the Car
    @Override
    public void getPrice(Customer c) {
        buy = c;
        String result = toString() + "\nThe customer's name is " + buy.getName()
                + " with salary RM" + buy.getSalary();
        if (buy.getSalary() > 8000) {
            result += " can buy this car. Your salary is higher than the price's car.";
        } else {
            result += " cannot buy this car. The car price is more than your salary.";
        }
        message = result;
        System.out.println(message);
    }
}
