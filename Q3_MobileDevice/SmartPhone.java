// SmartPhone.java
public class SmartPhone extends MobileDevice {
    public String type;

    public SmartPhone(String manufacturer, String model, String type) {
        super(manufacturer, model);
        this.type = type;
    }

    @Override
    public String getDetails() {
        return "SmartPhone Detail:\n" + super.getDetails() + "\nType: " + type;
    }

    @Override
    public void printDetails() {
        System.out.println(getDetails());
    }
}
