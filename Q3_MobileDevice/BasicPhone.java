// BasicPhone.java
public class BasicPhone extends MobileDevice {

    public BasicPhone(String manufacturer, String model) {
        super(manufacturer, model);
    }

    @Override
    public String getDetails() {
        return "Basic Phone Detail:\n" + super.getDetails();
    }

    @Override
    public void printDetails() {
        System.out.println(getDetails());
    }
}
