// DataSet.java
public class DataSet implements Measurable {
    private double sum;
    private Measurable max;
    private int count;
    private String name;

    public DataSet() {
        sum = 0;
        max = null;
        count = 0;
        name = "DataSet";
    }

    public void add(Measurable x) {
        sum = sum + x.getMeasure();
        if (count == 0 || max.getMeasure() < x.getMeasure()) {
            max = x;
        }
        count++;
    }

    public Measurable getMax() {
        return max;
    }

    public int getCount() {
        return count;
    }

    public double getAverage() {
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }

    // DataSet itself is Measurable: its measure is the average of its items
    @Override
    public double getMeasure() {
        return getAverage();
    }

    @Override
    public String getName() {
        return name;
    }
}
