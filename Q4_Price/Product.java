// Product.java
public class Product implements Price {
    private String type;
    private Customer buy;
    private String message = "";   // last result, so the GUI can display it

    public Product(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    // the subclasses (SmartPhone, Laptop) add the product name to this
    @Override
    public String toString() {
        return "The product is ";
    }

    // if the Customer's salary exceeds RM4000, the Customer buys the Product
    @Override
    public void getPrice(Customer c) {
        buy = c;
        String result = toString() + "\nThe customer's name is " + buy.getName()
                + " with salary RM" + buy.getSalary();
        if (buy.getSalary() > 4000) {
            result += " can buy this product.\nYour salary is higher than the price's product.";
        } else {
            result += " cannot buy this product.\nThe product price is more than your salary.";
        }
        message = result;
        System.out.println(message);
    }
}
