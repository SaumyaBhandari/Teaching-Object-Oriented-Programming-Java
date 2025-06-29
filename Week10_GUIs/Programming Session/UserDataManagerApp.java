import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDataManagerApp extends JFrame {

    // GUI Components
    private JTextField nameField, numberField, addressField, foodField;
    private JButton addButton;
    private JTable userDataTable;
    private DefaultTableModel tableModel; // To easily add/remove rows
    private JMenuBar menuBar;
    private JMenu fileMenu, helpMenu;
    private JMenuItem newItem, exitItem, aboutItem;

    public UserDataManagerApp() {
        super("User Data Manager"); // Set frame title
        setSize(800, 500); // Increased size for split pane
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen

        // 1. Initialize Table Model and Table
        String[] columnNames = {"Name", "Contact No.", "Address", "Favorite Food"};
        tableModel = new DefaultTableModel(columnNames, 0) { // 0 means initial no rows
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        userDataTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(userDataTable);

        // 2. Create Input Panel (Left Side)
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // Rows, 2 columns, gaps
        inputPanel.setBorder(BorderFactory.createTitledBorder("User Information"));

        nameField = new JTextField(20);
        numberField = new JTextField(20);
        addressField = new JTextField(20);
        foodField = new JTextField(20);

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Contact No.:"));
        inputPanel.add(numberField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Favorite Food:"));
        inputPanel.add(foodField);

        addButton = new JButton("Add User");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // For centering the button
        buttonPanel.add(addButton);
        inputPanel.add(new JLabel("")); // Empty label for spacing in grid layout
        inputPanel.add(buttonPanel); // Add button to the input panel

        // 3. Create the JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, tableScrollPane);
        splitPane.setDividerLocation(300); // Set initial divider position
        add(splitPane, BorderLayout.CENTER); // Add split pane to frame

        // 4. Set up the Menu Bar
        setupMenuBar();

        // 5. Add Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        setVisible(true);
    }

    private void setupMenuBar() {
        menuBar = new JMenuBar();

        // File Menu
        fileMenu = new JMenu("File");
        newItem = new JMenuItem("New");
        exitItem = new JMenuItem("Exit");

        newItem.addActionListener(e -> clearFields());
        exitItem.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?", "Exit Application",
                    JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Help Menu
        helpMenu = new JMenu("Help");
        aboutItem = new JMenuItem("About");

        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "User Data Manager v1.0\nDeveloped by Your Name\n" +
                "© 2025 All Rights Reserved.",
                "About", JOptionPane.INFORMATION_MESSAGE));

        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }

    private void addUser() {
        String name = nameField.getText().trim();
        String number = numberField.getText().trim();
        String address = addressField.getText().trim();
        String food = foodField.getText().trim();

        // Simple validation
        if (name.isEmpty() || number.isEmpty() || address.isEmpty() || food.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Add data to the table model
        // The Vector will automatically be converted to Object[] by DefaultTableModel
        tableModel.addRow(new Object[]{name, number, address, food});

        clearFields(); // Clear input fields after adding
        JOptionPane.showMessageDialog(this, "User added successfully!",
                                     "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        nameField.setText("");
        numberField.setText("");
        addressField.setText("");
        foodField.setText("");
    }

    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new UserDataManagerApp());
    }
}