
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EdibleGUI extends JFrame implements ActionListener {

    private JTextField nameField;
    private JComboBox<String> foodBox;
    private JTextArea resultArea;

    private JButton showButton;
    private JButton showAllButton;
    private JButton clearButton;

    public EdibleGUI() {

        setTitle("Edible GUI Application");
        setSize(550, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = new JLabel(
            "Edible GUI Application", SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        add(titleLabel, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        );

        inputPanel.add(new JLabel("Person Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Select Food:"));

        String[] foods = {
            "Chicken",
            "Red Apple",
            "Navel Orange"
        };

        foodBox = new JComboBox<>(foods);
        inputPanel.add(foodBox);

        // Result area
        resultArea = new JTextArea(7, 30);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Center panel
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        centerPanel.setBorder(
            BorderFactory.createEmptyBorder(0, 20, 10, 20)
        );

        add(centerPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        showButton = new JButton("Show How to Eat");
        showAllButton = new JButton("Show Original Output");
        clearButton = new JButton("Clear");

        buttonPanel.add(showButton);
        buttonPanel.add(showAllButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Event handling
        showButton.addActionListener(this);
        showAllButton.addActionListener(this);
        clearButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == showButton) {

            String personName = nameField.getText().trim();
            String selectedFood =
                (String) foodBox.getSelectedItem();

            if (personName.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a person name.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Edible food;

            if (selectedFood.equals("Chicken")) {
                food = new Animal("Chicken");
            }
            else if (selectedFood.equals("Red Apple")) {
                food = new Apple("Red Apple");
            }
            else {
                food = new Orange("Navel Orange");
            }

            Person person = new Person(personName);

            resultArea.setText(person.eating(food));
        }

        else if (e.getSource() == showAllButton) {

            Edible[] objects = {
                new Animal("Chicken"),
                new Apple("Red Apple"),
                new Orange("Navel Orange")
            };

            Person p1 = new Person("Ali");
            Person p2 = new Person("Sarah");

            StringBuilder output = new StringBuilder();

            for (Edible food : objects) {

                if (food instanceof Animal) {
                    output.append(p1.eating(food));
                }
                else {
                    output.append(p2.eating(food));
                }

                output.append("\n\n");
            }

            resultArea.setText(output.toString());
        }

        else if (e.getSource() == clearButton) {

            nameField.setText("");
            foodBox.setSelectedIndex(0);
            resultArea.setText("");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new EdibleGUI());

    }
}
