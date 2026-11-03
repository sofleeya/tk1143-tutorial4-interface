import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// GUI for Question 4 (PriceApp)
public class PriceGUI extends JFrame implements ActionListener {

    private JComboBox<String> cbItem;
    private JTextField tfItemName, tfPrice, tfCustomer, tfSalary;
    private JLabel lblItemName;
    private JButton btnCheck, btnSample, btnClear;
    private JTextArea taOutput;

    public PriceGUI() {
        setTitle("Price App");
        setSize(560, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout(5, 5));

        // ---------- input panel ----------
        JPanel pnlInput = new JPanel(new GridLayout(5, 2, 5, 5));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Can the customer buy it?"));

        cbItem = new JComboBox<String>(new String[] { "SmartPhone", "Laptop", "Car" });
        lblItemName = new JLabel("Product name :");
        tfItemName = new JTextField(10);
        tfPrice = new JTextField(10);
        tfCustomer = new JTextField(10);
        tfSalary = new JTextField(10);

        pnlInput.add(new JLabel("Item :"));
        pnlInput.add(cbItem);
        pnlInput.add(lblItemName);
        pnlInput.add(tfItemName);
        pnlInput.add(new JLabel("Price (RM) :"));
        pnlInput.add(tfPrice);
        pnlInput.add(new JLabel("Customer's name :"));
        pnlInput.add(tfCustomer);
        pnlInput.add(new JLabel("Salary (RM) :"));
        pnlInput.add(tfSalary);

        // ---------- output ----------
        taOutput = new JTextArea();
        taOutput.setEditable(false);
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);
        taOutput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        // ---------- buttons ----------
        JPanel pnlButtons = new JPanel(new FlowLayout());
        btnCheck = new JButton("Check");
        btnSample = new JButton("Run Sample");
        btnClear = new JButton("Clear");
        pnlButtons.add(btnCheck);
        pnlButtons.add(btnSample);
        pnlButtons.add(btnClear);

        pane.add(pnlInput, BorderLayout.NORTH);
        pane.add(new JScrollPane(taOutput), BorderLayout.CENTER);
        pane.add(pnlButtons, BorderLayout.SOUTH);

        // register listeners
        cbItem.addActionListener(this);
        btnCheck.addActionListener(this);
        btnSample.addActionListener(this);
        btnClear.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbItem) {
            // a car has a brand, the others have a product name
            lblItemName.setText(cbItem.getSelectedIndex() == 2 ? "Car brand :" : "Product name :");
        } else if (src == btnCheck) {
            check();
        } else if (src == btnSample) {
            runSample();
        } else if (src == btnClear) {
            tfItemName.setText("");
            tfPrice.setText("");
            tfCustomer.setText("");
            tfSalary.setText("");
            taOutput.setText("");
        }
    }

    private void check() {
        String itemName = tfItemName.getText().trim();
        String custName = tfCustomer.getText().trim();
        double price, salary;

        if (itemName.isEmpty() || custName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the item name and the customer's name.",
                    "Missing input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            price = Double.parseDouble(tfPrice.getText().trim());
            salary = Double.parseDouble(tfSalary.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Price and salary must be numbers.",
                    "Invalid input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Customer cust = new Customer(custName, salary);
        switch (cbItem.getSelectedIndex()) {
            case 0:
                taOutput.append(resultOf(new SmartPhone("SmartPhone", itemName, price), cust) + "\n\n");
                break;
            case 1:
                taOutput.append(resultOf(new Laptop("Laptop", itemName, price), cust) + "\n\n");
                break;
            default:
                taOutput.append(resultOf(new Car(itemName, price), cust) + "\n\n");
        }
    }

    // calls getPrice() on any Price object and returns the text it produced
    private String resultOf(Price item, Customer c) {
        item.getPrice(c);
        if (item instanceof Product) {
            return ((Product) item).getMessage();
        }
        return ((Car) item).getMessage();
    }

    // same objects and logic as PriceApp.main()
    private void runSample() {
        SmartPhone sp = new SmartPhone("SmartPhone", "Iphone 14 ProMax", 5500.00);
        Laptop lp = new Laptop("Laptop", "Lenovo Yoga", 6000.00);
        Car c = new Car("BMW", 200000);
        Customer c1 = new Customer("Sally", 3000.00);
        Customer c2 = new Customer("Aaron", 8000.00);
        Customer c3 = new Customer("Simon", 10000.00);

        taOutput.setText("");
        taOutput.append(resultOf(sp, c1) + "\n\n");
        taOutput.append(resultOf(lp, c2) + "\n\n");
        taOutput.append(resultOf(c, c3) + "\n");
    }

    public static void main(String[] args) {
        PriceGUI frame = new PriceGUI();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
