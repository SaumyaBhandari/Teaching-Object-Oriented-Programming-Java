# Unit VII: GUI Programming with Swing (Continued)

## Objective:
Further enhance your understanding of Swing by learning **advanced components** like JTable, JMenuBar, JDialog, and JOptionPane, and apply them to build more sophisticated interactive applications.

## 7.5 Advanced Swing Components

We've covered the basics, but Swing offers much more! Let's dive into some powerful components that help build robust applications.

### 7.5.1 JTable for Displaying Tabular Data

The **JTable** component is used to display data in a two-dimensional table format, just like a spreadsheet. It's incredibly versatile for presenting structured information.

**What is JTable?**
`JTable` allows you to arrange data in rows and columns. It's highly customizable, letting you control column headers, cell rendering, and editing.

**Example**: Imagine you have a list of students with their names, ages, and grades. A `JTable` is perfect for displaying this information clearly.

```java
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane; // Essential for tables to show scrollbars
import java.awt.BorderLayout;

public class JTableExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTable Example");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Using BorderLayout for better table placement

        // 1. Define Column Headers
        String[] columnNames = {"Name", "Age", "City"};

        // 2. Define Data for the Table (as a 2D array of Objects)
        Object[][] data = {
            {"Alice", 25, "Kathmandu"},
            {"Bob", 30, "Pokhara"},
            {"Charlie", 22, "Biratnagar"},
            {"David", 35, "Lalitpur"}
        };

        // 3. Create the JTable
        JTable table = new JTable(data, columnNames);

        // 4. Wrap the JTable in a JScrollPane
        // This is crucial! If your table has more rows than can fit,
        // the JScrollPane provides scrollability.
        JScrollPane scrollPane = new JScrollPane(table);

        // 5. Add the JScrollPane (containing the table) to the frame
        frame.add(scrollPane, BorderLayout.CENTER); // Place in the center

        frame.setVisible(true);
    }
}
```

When you run this, you'll see a window with a table displaying the names, ages, and cities, complete with column headers and a scrollbar (though not active in this small example).

**Customizing JTable (Basic):**
You can set properties like column widths, disable editing, or select single rows.

**Example**: Let's make the table cells non-editable by default.

```java
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel; // Important for non-editable tables
import java.awt.BorderLayout;

public class JTableEditableExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTable Non-Editable Example");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"Name", "Age", "City"};
        Object[][] data = {
            {"Alice", 25, "Kathmandu"},
            {"Bob", 30, "Pokhara"}
        };

        // Create a custom table model to control editability
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // All cells non-editable
                return false;
            }
        };

        JTable table = new JTable(model); // Use the custom model
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
```

Now, if you try to double-click on a cell, you won't be able to edit its content. This is a common requirement for display-only tables.

### 7.5.2 JMenuBar, JMenu, and JMenuItem - Creating Menus

Menus are a standard part of most desktop applications, providing organized access to various commands and options. In Swing, you use **JMenuBar**, **JMenu**, and **JMenuItem** to build them.

**What are JMenuBar, JMenu, JMenuItem?**

* **JMenuBar**: This is the horizontal bar usually found at the top of a frame, holding one or more menus.
* **JMenu**: This is a single menu item that, when clicked, reveals a dropdown list of `JMenuItem`s or sub-menus. For example, "File", "Edit", "Help".
* **JMenuItem**: This is an individual selectable item within a `JMenu`. Clicking a `JMenuItem` typically performs an action. For example, "Open", "Save", "Exit".

**Example**: Let's create a simple menu bar with a "File" menu and "Open" and "Exit" options.

```java
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane; // For simple message dialog

public class JMenuExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. Create a JMenuBar
        JMenuBar menuBar = new JMenuBar();

        // 2. Create a JMenu (e.g., "File")
        JMenu fileMenu = new JMenu("File");

        // 3. Create JMenuItems
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem exitItem = new JMenuItem("Exit");

        // 4. Add ActionListeners to JMenuItems
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Open option selected!");
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to exit?", "Exit Application",
                    JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0); // Terminate the application
                }
            }
        });

        // 5. Add JMenuItems to the JMenu
        fileMenu.add(openItem);
        fileMenu.addSeparator(); // Adds a horizontal line separator
        fileMenu.add(exitItem);

        // 6. Add JMenu to the JMenuBar
        menuBar.add(fileMenu);

        // 7. Set the JMenuBar for the JFrame
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }
}
```

When you run this, you'll see a "File" menu in the top-left corner. Clicking it reveals "Open" and "Exit". Clicking "Open" shows a small pop-up, and clicking "Exit" asks for confirmation before closing the application.

### 7.5.3 JDialog - Custom Pop-up Windows

A **JDialog** is a top-level window that is dependent on another frame. It's often used for pop-up messages, input forms, or secondary windows that require user interaction before the main application can proceed.

**What is JDialog?**
Unlike `JFrame`, which is an independent window, a `JDialog` usually has a parent `JFrame`. Dialogs can be **modal** (meaning the parent frame cannot be interacted with until the dialog is closed) or non-modal.

**Example**: Let's create a simple custom dialog that asks for a user's favorite color.

```java
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDialogExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Application Frame");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel statusLabel = new JLabel("No color entered yet.");
        frame.add(statusLabel);

        JButton openDialogButton = new JButton("Enter Favorite Color");
        frame.add(openDialogButton);

        openDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom JDialog
                JDialog colorDialog = new JDialog(frame, "Favorite Color Input", true); // 'true' makes it modal
                colorDialog.setSize(300, 150);
                colorDialog.setLayout(new FlowLayout());
                colorDialog.setLocationRelativeTo(frame); // Center dialog relative to parent frame

                JLabel promptLabel = new JLabel("What is your favorite color?");
                JTextField colorTextField = new JTextField(15);
                JButton okButton = new JButton("OK");

                colorDialog.add(promptLabel);
                colorDialog.add(colorTextField);
                colorDialog.add(okButton);

                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String favoriteColor = colorTextField.getText();
                        statusLabel.setText("Favorite Color: " + favoriteColor);
                        colorDialog.dispose(); // Close the dialog
                    }
                });

                colorDialog.setVisible(true); // Make the dialog visible (blocks parent frame if modal)
            }
        });

        frame.setVisible(true);
    }
}
```

When you run this, you'll see the main frame. Click "Enter Favorite Color," and a new, smaller dialog will appear. While this dialog is open, you cannot interact with the main frame (because it's modal). Once you type a color and click "OK," the dialog closes, and the main frame's label updates.

### 7.5.4 JOptionPane - Standard Pop-up Dialogs

**JOptionPane** provides easy-to-use static methods for displaying standard dialog boxes. These are great for simple messages, confirmations, warnings, or basic input without having to create a custom `JDialog` from scratch.

**What is JOptionPane?**
It's a utility class that simplifies creating common dialogs:

* **Message Dialogs**: Display a message (e.g., information, warning, error).
* **Confirmation Dialogs**: Ask a yes/no/cancel question.
* **Input Dialogs**: Prompt the user for a single line of input.
* **Option Dialogs**: Allow the user to choose from a set of predefined options.

**Example: Message Dialog**

```java
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JOptionPaneMessageExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JOptionPane Message Demo");
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton infoButton = new JButton("Show Info");
        frame.add(infoButton);

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show an information message dialog
                JOptionPane.showMessageDialog(frame, "This is an information message.",
                                             "Info Title", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton warningButton = new JButton("Show Warning");
        frame.add(warningButton);
        warningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a warning message dialog
                JOptionPane.showMessageDialog(frame, "Beware! This is a warning.",
                                             "Warning!", JOptionPane.WARNING_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}
```

Run this, and click the buttons to see standard pop-up messages with different icons (information 'i', warning '!') and titles.

**Example: Confirmation Dialog**

```java
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JOptionPaneConfirmExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JOptionPane Confirm Demo");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton confirmButton = new JButton("Confirm Action");
        JLabel resultLabel = new JLabel("No action confirmed yet.");
        frame.add(confirmButton);
        frame.add(resultLabel);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(frame,
                    "Do you want to proceed?", "Confirmation",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    resultLabel.setText("You chose: Yes");
                } else if (response == JOptionPane.NO_OPTION) {
                    resultLabel.setText("You chose: No");
                } else if (response == JOptionPane.CANCEL_OPTION) {
                    resultLabel.setText("You chose: Cancel");
                } else { // CLOSED_OPTION
                    resultLabel.setText("Dialog closed without choice.");
                }
            }
        });

        frame.setVisible(true);
    }
}
```

Click "Confirm Action" to get a dialog with "Yes," "No," and "Cancel" buttons. The `resultLabel` will update based on your choice.

**Example: Input Dialog**

```java
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JOptionPaneInputExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JOptionPane Input Demo");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton inputButton = new JButton("Enter Your Name");
        JLabel nameLabel = new JLabel("Name: ");
        frame.add(inputButton);
        frame.add(nameLabel);

        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Please enter your name:");
                if (name != null && !name.trim().isEmpty()) {
                    nameLabel.setText("Name: " + name);
                } else {
                    nameLabel.setText("Name: (No name entered)");
                }
            }
        });

        frame.setVisible(true);
    }
}
```

Click "Enter Your Name" to get an input box. Whatever you type and click "OK" on will update the `nameLabel`. If you click "Cancel" or close the dialog, name will be `null`.

## 7.6 Building a Simple Interactive User Data Management Application

Now, let's combine several of these components to create a more functional application. We will build a simple "**User Data Manager**" that allows users to input name, number, address, and favorite food, display this data in a table, and offer menu options.

**Application Features:**

* **User Input Panel (Left)**: `JLabel`s and `JTextField`s for Name, Number, Address, and Favorite Food. A `JButton` to "Add User".
* **User Data Table (Right)**: A `JTable` to display all added user data.
* **Menu Bar**:
    * **File Menu**: "New" (clear fields), "Exit".
    * **Help Menu**: "About" (shows `JOptionPane.showMessageDialog`).

To achieve the left/right split, we'll use a **JSplitPane**.

```java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector; // Used by DefaultTableModel internally

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
```

**Explanation of the Application Code:**

* `UserDataManagerApp` **extends JFrame**: We make our main application class extend `JFrame` directly, making it the main window.
* **DefaultTableModel for JTable**:
    * Instead of directly passing `Object[][]` data to `JTable`, we create a **DefaultTableModel**. This is crucial because it allows us to dynamically `addRow()` and potentially `removeRow()` data from the table after it's displayed.
    * We override `isCellEditable` to make the table cells read-only.
* **Input Panel (JPanel with GridLayout)**:
    * We create a `JPanel` specifically for the input fields.
    * `GridLayout(0, 2, 10, 10)` means: any number of rows (`0`), exactly two columns, and 10 pixels horizontal and vertical gap between components. This neatly aligns labels and text fields.
    * `BorderFactory.createTitledBorder()` gives a nice visual border with a title.
    * A `FlowLayout` `JPanel` is used within the `GridLayout` to center the "Add User" button.
* **JSplitPane**:
    * `new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, tableScrollPane)` creates a split pane that divides its space horizontally. `inputPanel` is on the left, and `tableScrollPane` (containing the `JTable`) is on the right.
    * `setDividerLocation(300)` sets the initial width of the left panel to 300 pixels.
* `setupMenuBar()` **Method**:
    * This separate method organizes the creation of `JMenuBar`, `JMenu`s (File, Help), and `JMenuItem`s (New, Exit, About).
    * `JMenuItem`s are assigned `ActionListener`s to perform specific tasks.
    * The "Exit" option uses `JOptionPane.showConfirmDialog` for user confirmation.
    * The "About" option uses `JOptionPane.showMessageDialog` to display information.
* `addUser()` **Method**:
    * Retrieves text from all `JTextField`s.
    * Includes a simple validation using `isEmpty()` checks and displays a `JOptionPane.warningMessageDialog` if fields are empty.
    * If valid, `tableModel.addRow(new Object[]{...})` is used to add a new row of data to the table.
    * `clearFields()` is called to reset the input fields for the next entry.
    * A `JOptionPane.informationMessageDialog` confirms success.
* `clearFields()` **Method**: Resets all `JTextField`s to empty strings.
* `main` **Method (SwingUtilities.invokeLater)**:
    * `SwingUtilities.invokeLater(() -> new UserDataManagerApp());` is a best practice. It ensures that the GUI creation and update code runs on the **Event Dispatch Thread (EDT)**, which is the dedicated thread for Swing operations. This prevents potential threading issues and ensures responsiveness, especially in more complex applications.

This application demonstrates a practical combination of `JTable` for data display, input fields for user interaction, a `JSplitPane` for layout management, and a `JMenuBar` with `JOptionPane` for common application features, providing a solid foundation for building more advanced GUI applications.