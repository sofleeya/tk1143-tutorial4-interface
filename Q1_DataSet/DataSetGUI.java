import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;


public class DataSetGUI extends JFrame implements ActionListener {

    private JComboBox<String> cbType;
    private JLabel lblName, lblValue;
    private JTextField tfName, tfValue;
    private JButton btnAdd, btnCalc, btnSample, btnReset;
    private JTextArea taOutput;

    private DataSet bankData, coinData, studentData;
    private DecimalFormat df = new DecimalFormat("0.00");

    public DataSetGUI() {
        setTitle("DataSet - Measurable");
        setSize(480, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        resetData();

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout(5, 5));

        // ---------- input panel (top) ----------
        JPanel pnlInput = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Add data"));

        cbType = new JComboBox<String>(new String[] { "Bank Account", "Coin", "Student" });
        lblName = new JLabel("Name :");
        tfName = new JTextField(10);
        lblValue = new JLabel("Balance (RM) :");
        tfValue = new JTextField(10);

        pnlInput.add(new JLabel("Data type :"));
        pnlInput.add(cbType);
        pnlInput.add(lblName);
        pnlInput.add(tfName);
        pnlInput.add(lblValue);
        pnlInput.add(tfValue);

        // ---------- output area (centre) ----------
        taOutput = new JTextArea();
        taOutput.setEditable(false);
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);
        taOutput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(taOutput);

        // ---------- buttons (bottom) ----------
        JPanel pnlButtons = new JPanel(new FlowLayout());
        btnAdd = new JButton("Add");
        btnCalc = new JButton("Calculate");
        btnSample = new JButton("Load Sample");
        btnReset = new JButton("Reset");
        pnlButtons.add(btnAdd);
        pnlButtons.add(btnCalc);
        pnlButtons.add(btnSample);
        pnlButtons.add(btnReset);

        pane.add(pnlInput, BorderLayout.NORTH);
        pane.add(scroll, BorderLayout.CENTER);
        pane.add(pnlButtons, BorderLayout.SOUTH);

        // register this class as the event listener
        cbType.addActionListener(this);
        btnAdd.addActionListener(this);
        btnCalc.addActionListener(this);
        btnSample.addActionListener(this);
        btnReset.addActionListener(this);

        updateFields();
    }

    private void resetData() {
        bankData = new DataSet();
        coinData = new DataSet();
        studentData = new DataSet();
    }

    private DataSet currentData() {
        switch (cbType.getSelectedIndex()) {
            case 0:  return bankData;
            case 1:  return coinData;
            default: return studentData;
        }
    }

    
    private void updateFields() {
        int type = cbType.getSelectedIndex();
        if (type == 0) {
            lblValue.setText("Balance (RM) :");
            tfName.setText("");
            tfName.setEnabled(false);
        } else if (type == 1) {
            lblName.setText("Coin name :");
            lblValue.setText("Coin value :");
            tfName.setEnabled(true);
        } else {
            lblName.setText("Student name :");
            lblValue.setText("Height (cm) :");
            tfName.setEnabled(true);
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbType) {
            updateFields();
        } else if (src == btnAdd) {
            addItem();
        } else if (src == btnCalc) {
            calculate();
        } else if (src == btnSample) {
            loadSample();
        } else if (src == btnReset) {
            resetData();
            taOutput.setText("");
            tfName.setText("");
            tfValue.setText("");
        }
    }

    private void addItem() {
        int type = cbType.getSelectedIndex();
        String name = tfName.getText().trim();
        double value;
        try {
            value = Double.parseDouble(tfValue.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number in the value field.",
                    "Invalid input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (type != 0 && name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name.",
                    "Missing name", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (type == 0) {
            BankAccount acc = new BankAccount(value);
            bankData.add(acc);
            taOutput.append("Added " + acc.getName() + " : RM" + df.format(value) + "\n");
        } else if (type == 1) {
            coinData.add(new Coin(value, name));
            taOutput.append("Added coin " + name + " : " + df.format(value) + "\n");
        } else {
            studentData.add(new Student(name, value));
            taOutput.append("Added student " + name + " : " + df.format(value) + " cm\n");
        }
        tfName.setText("");
        tfValue.setText("");
    }

    private void calculate() {
        DataSet ds = currentData();
        if (ds.getCount() == 0) {
            JOptionPane.showMessageDialog(this, "No data added yet for this type.",
                    "Empty data set", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Measurable max = ds.getMax();
        taOutput.append("\n");
        switch (cbType.getSelectedIndex()) {
            case 0:
                taOutput.append("Bank Average Balance: " + ds.getAverage() + "\n");
                taOutput.append("Bank Highest Balance: " + max.getMeasure() + "\n");
                break;
            case 1:
                taOutput.append("Average Coin Value: " + df.format(ds.getAverage()) + "\n");
                taOutput.append("Highest Coin Balance: " + df.format(max.getMeasure()) + "\n");
                taOutput.append("Highest Coin's Name: " + max.getName() + "\n");
                break;
            default:
                taOutput.append("Average Student's Height: " + df.format(ds.getAverage()) + "\n");
                taOutput.append("Tallest Height: " + df.format(max.getMeasure()) + "\n");
                taOutput.append("Tallest Student's Name: " + max.getName() + "\n");
        }
        taOutput.append("\n");
    }

    // loads the data from the DataSetApp sample and shows the sample output
    private void loadSample() {
        resetData();
        bankData.add(new BankAccount(0));
        bankData.add(new BankAccount(10000));
        bankData.add(new BankAccount(2000));
        coinData.add(new Coin(0.25, "Quarter"));
        coinData.add(new Coin(0.1, "Dime"));
        coinData.add(new Coin(0.05, "Nickel"));
        studentData.add(new Student("Ali", 157.50));
        studentData.add(new Student("Alex", 165.00));
        studentData.add(new Student("Maria", 147.00));

        taOutput.setText("");
        taOutput.append("Bank Average Balance: " + bankData.getAverage() + "\n");
        taOutput.append("Bank Highest Balance: " + bankData.getMax().getMeasure() + "\n");
        taOutput.append("Average Coin Value: " + df.format(coinData.getAverage()) + "\n");
        taOutput.append("Highest Coin Balance: " + df.format(coinData.getMax().getMeasure()) + "\n");
        taOutput.append("Highest Coin's Name: " + coinData.getMax().getName() + "\n");
        taOutput.append("Average Student's Height: " + df.format(studentData.getAverage()) + "\n");
        taOutput.append("Tallest Height: " + df.format(studentData.getMax().getMeasure()) + "\n");
        taOutput.append("Tallest Student's Name: " + studentData.getMax().getName() + "\n");
    }

    public static void main(String[] args) {
        DataSetGUI frame = new DataSetGUI();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
