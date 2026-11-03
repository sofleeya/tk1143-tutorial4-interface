import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// GUI for Question 2 (EdibleApp)
public class EdibleGUI extends JFrame implements ActionListener {

    private JTextField tfPerson, tfItem;
    private JComboBox<String> cbType;
    private JButton btnEat, btnSample, btnClear;
    private JTextArea taOutput;

    public EdibleGUI() {
        setTitle("Edible App");
        setSize(560, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout(5, 5));

        // ---------- input panel ----------
        JPanel pnlInput = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Who is eating what?"));

        tfPerson = new JTextField(10);
        cbType = new JComboBox<String>(new String[] { "Animal", "Apple", "Orange" });
        tfItem = new JTextField(10);

        pnlInput.add(new JLabel("Person's name :"));
        pnlInput.add(tfPerson);
        pnlInput.add(new JLabel("Type of edible :"));
        pnlInput.add(cbType);
        pnlInput.add(new JLabel("Name of edible :"));
        pnlInput.add(tfItem);

        // ---------- output ----------
        taOutput = new JTextArea();
        taOutput.setEditable(false);
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);
        taOutput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        // ---------- buttons ----------
        JPanel pnlButtons = new JPanel(new FlowLayout());
        btnEat = new JButton("Eat");
        btnSample = new JButton("Run Sample");
        btnClear = new JButton("Clear");
        pnlButtons.add(btnEat);
        pnlButtons.add(btnSample);
        pnlButtons.add(btnClear);

        pane.add(pnlInput, BorderLayout.NORTH);
        pane.add(new JScrollPane(taOutput), BorderLayout.CENTER);
        pane.add(pnlButtons, BorderLayout.SOUTH);

        // register listeners
        btnEat.addActionListener(this);
        btnSample.addActionListener(this);
        btnClear.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnEat) {
            eat();
        } else if (src == btnSample) {
            runSample();
        } else if (src == btnClear) {
            taOutput.setText("");
            tfPerson.setText("");
            tfItem.setText("");
        }
    }

    private void eat() {
        String person = tfPerson.getText().trim();
        String item = tfItem.getText().trim();
        if (person.isEmpty() || item.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the person's name and the edible's name.",
                    "Missing input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Edible edible;                      // polymorphism: one Edible variable, three possible objects
        switch (cbType.getSelectedIndex()) {
            case 0:  edible = new Animal(item); break;
            case 1:  edible = new Apple(item);  break;
            default: edible = new Orange(item);
        }
        Person p = new Person(person);
        taOutput.append(p.eating(edible) + "\n");
    }

    // same objects and logic as EdibleApp.main()
    private void runSample() {
        Edible[] objects = { new Animal("Chicken"), new Apple("Red Apple"), new Orange("Navel Orange") };
        Person p1 = new Person("Ali");
        Person p2 = new Person("Sarah");

        taOutput.setText("");
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof Animal) {
                taOutput.append(p1.eating(objects[i]) + "\n");
            } else {
                taOutput.append(p2.eating(objects[i]) + "\n");
            }
        }
    }

    public static void main(String[] args) {
        EdibleGUI frame = new EdibleGUI();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
