// MobileDevice.java
public class MobileDevice implements RadioTuner {
    protected String manufacturer;
    protected String model;
    public String station;       // public, because MobileApp reads m1.station directly
    public double frequency;     // public, because MobileApp reads m1.frequency directly

    public MobileDevice(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    @Override
    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    // returns the details as text (used by the GUI)
    public String getDetails() {
        return "Manufacturer: " + manufacturer + "\nModel: " + model;
    }

    public void printDetails() {
        System.out.println(getDetails());
    }
}
