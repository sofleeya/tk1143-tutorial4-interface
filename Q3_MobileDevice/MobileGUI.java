import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// GUI for Question 3 (MobileApp)
public class MobileGUI extends JFrame implements ActionListener {

    private JComboBox<String> cbType;
    private JTextField tfManufacturer, tfModel, tfType, tfStation, tfFrequency;
    private JLabel lblType;
    private JButton btnShow, btnSample, btnClear;
    private JTextArea taOutput;

    public MobileGUI() {
        setTitle("Mobile Device");
        setSize(480, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout(5, 5));

        // ---------- input panel ----------
        JPanel pnlInput = new JPanel(new GridLayout(6, 2, 5, 5));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Device information"));

        cbType = new JComboBox<String>(new String[] { "SmartPhone", "BasicPhone" });
        tfManufacturer = new JTextField(10);
        tfModel = new JTextField(10);
        lblType = new JLabel("Type :");
        tfType = new JTextField(10);
        tfStation = new JTextField(10);
        tfFrequency = new JTextField(10);

        pnlInput.add(new JLabel("Device type :"));
        pnlInput.add(cbType);
        pnlInput.add(new JLabel("Manufacturer :"));
        pnlInput.add(tfManufacturer);
        pnlInput.add(new JLabel("Model :"));
        pnlInput.add(tfModel);
        pnlInput.add(lblType);
        pnlInput.add(tfType);
        pnlInput.add(new JLabel("Radio station :"));
        pnlInput.add(tfStation);
        pnlInput.add(new JLabel("Frequency :"));
        pnlInput.add(tfFrequency);

        // ---------- output ----------
        taOutput = new JTextArea();
        taOutput.setEditable(false);
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);
        taOutput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        // ---------- buttons ----------
        JPanel pnlButtons = new JPanel(new FlowLayout());
        btnShow = new JButton("Show Details");
        btnSample = new JButton("Run Sample");
        btnClear = new JButton("Clear");
        pnlButtons.add(btnShow);
        pnlButtons.add(btnSample);
        pnlButtons.add(btnClear);

        pane.add(pnlInput, BorderLayout.NORTH);
        pane.add(new JScrollPane(taOutput), BorderLayout.CENTER);
        pane.add(pnlButtons, BorderLayout.SOUTH);

        // register listeners
        cbType.addActionListener(this);
        btnShow.addActionListener(this);
        btnSample.addActionListener(this);
        btnClear.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbType) {
            boolean smart = cbType.getSelectedIndex() == 0;
            tfType.setEnabled(smart);        // only a SmartPhone has a type
            lblType.setEnabled(smart);
            if (!smart) {
                tfType.setText("");
            }
        } else if (src == btnShow) {
            showDetails();
        } else if (src == btnSample) {
            runSample();
        } else if (src == btnClear) {
            tfManufacturer.setText("");
            tfModel.setText("");
            tfType.setText("");
            tfStation.setText("");
            tfFrequency.setText("");
            taOutput.setText("");
        }
    }

    private void showDetails() {
        String manufacturer = tfManufacturer.getText().trim();
        String model = tfModel.getText().trim();
        String type = tfType.getText().trim();
        String station = tfStation.getText().trim();
        double frequency;

        if (manufacturer.isEmpty() || model.isEmpty()
                || (cbType.getSelectedIndex() == 0 && type.isEmpty())) {
            JOptionPane.showMessageDialog(this, "Please fill in all the required fields.",
                    "Missing input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            frequency = Double.parseDouble(tfFrequency.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Frequency must be a number (e.g. 97.6).",
                    "Invalid input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MobileDevice m;                      // polymorphism: a MobileDevice reference
        if (cbType.getSelectedIndex() == 0) {
            m = new SmartPhone(manufacturer, model, type);
        } else {
            m = new BasicPhone(manufacturer, model);
        }
        m.setStation(station);
        m.setFrequency(frequency);

        taOutput.append(describe(m) + "\n");
    }

    // same objects and logic as MobileApp.main()
    private void runSample() {
        MobileDevice m1 = new SmartPhone("Samsung", "S20", "Galaxy");
        MobileDevice m2 = new BasicPhone("Sonny Ericson", "Walkman");
        m1.setStation("Hot FM");
        m1.setFrequency(97.60);
        m2.setStation("Fly FM");
        m2.setFrequency(109.8);

        taOutput.setText("");
        taOutput.append(describe(m1) + "\n");
        taOutput.append(describe(m2) + "\n");
    }

    private String describe(MobileDevice m) {
        return m.getDetails() + "\nStation:" + m.station + "\nFrequency:" + m.frequency + "\n";
    }

    public static void main(String[] args) {
        MobileGUI frame = new MobileGUI();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
