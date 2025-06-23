# Unit VII: GUI Programming with Swing (8 hours)

## Objective:
By the end of this unit, you will be able to:

* Design **GUI creation** with Swing components, including buttons, labels, text fields, and text areas.
* Gain expertise in **event-driven programming** for mouse and key events.
* Build simple **interactive applications** using Swing.

## 7.1 Introduction to Graphical User Interface (GUI) Programming in Java

Welcome to the exciting world of GUI programming in Java! Until now, you've probably been interacting with your Java programs through the console – typing input and seeing text output. While that's fine for many applications, most modern software you use daily, like web browsers, word processors, or even games, relies on a **Graphical User Interface (GUI)**. A GUI provides a visual way for users to interact with a program using elements like buttons, menus, text boxes, and images.

### What is a GUI?
A **GUI** (pronounced "gooey") is a visual interface that allows users to interact with electronic devices through graphical icons and visual indicators, as opposed to text-based interfaces, typed command labels, or text navigation.

**Example**: Think about opening a file on your computer. Instead of typing `open mydocument.docx` in a command prompt, you click on a "File Explorer" icon, then double-click on the "mydocument.docx" icon. This visual interaction is a GUI at work!

### Why use GUI in Java?
Java provides powerful libraries for creating GUIs. The two main ones are **AWT** (Abstract Window Toolkit) and **Swing**. While AWT was the original, Swing is much more powerful, flexible, and widely used today. Swing provides a richer set of components and offers a "look and feel" that can be customized to resemble the native operating system or have its own unique appearance.

**Example**: Imagine you're building a simple calculator. With a console application, you'd type numbers and operators. With a Swing GUI, you'd have buttons for numbers (0-9), operators (+, -, *, /), and a display area to show the results, just like a real calculator. This is much more intuitive for the user!

## 7.2 Using Swing Components (buttons, labels, text fields, text area, etc.)

Swing provides a rich set of pre-built components that you can drag and drop (conceptually, in your code) onto your application's window. These components are the building blocks of your GUI. Let's explore some of the most common ones.

To use Swing components, you typically need to import classes from the `javax.swing` package.

### Basic Structure of a Swing Application:
Every Swing application usually starts with a top-level container, most commonly **JFrame**. A `JFrame` represents a window with a title bar, borders, and buttons for minimizing, maximizing, and closing.

**Example**: To create a basic window, you would write:

```java
import javax.swing.JFrame;

public class MyFirstGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My First Swing Window"); // Create a JFrame with a title
        frame.setSize(400, 300); // Set the width and height of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        frame.setVisible(true); // Make the window visible
    }
}
```

When you run this code, you'll see an empty window titled "My First Swing Window."
Now, let's add some actual components inside this `JFrame`.

### JLabel - Displaying Text and Images:
A **JLabel** is used to display a short text string or an image, or both. It does not react to user input.

**Example**: Let's add a "Hello, Swing!" label to our window:

```java
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LabelExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Label Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello, Swing!"); // Create a JLabel with text
        frame.add(label); // Add the label to the frame

        frame.setVisible(true);
    }
}
```

When you run this, you'll see "Hello, Swing!" displayed in the top-left corner of the window.

### JButton - The Clickable Button:
A **JButton** is a push button. When the user clicks on it, it generates an "action event" that your program can respond to.

**Example**: Let's add a "Click Me!" button:

```java
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout; // We'll need this for better layout later

public class ButtonExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout()); // Arrange components in a row

        JButton button = new JButton("Click Me!"); // Create a JButton with text
        frame.add(button); // Add the button to the frame

        frame.setVisible(true);
    }
}
```

You'll now see a "Click Me!" button in your window. Right now, clicking it doesn't do anything because we haven't handled the event yet (we'll cover this in the next section!).

### JTextField - Single Line Text Input:
A **JTextField** allows the user to enter and edit a single line of text.

**Example**: Let's add a text field where the user can type their name:

```java
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class TextFieldExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Text Field Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField textField = new JTextField(20); // Create a JTextField with a width of 20 columns
        frame.add(new JLabel("Enter your name:")); // Add a label for the text field
        frame.add(textField); // Add the text field

        frame.setVisible(true);
    }
}
```

You'll see a label "Enter your name:" followed by a box where you can type text.

### JTextArea - Multi-line Text Input:
A **JTextArea** allows the user to enter and edit multiple lines of text. It's great for comments, messages, or longer inputs.

**Example**: Let's create a notes area:

```java
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane; // For scrollability
import java.awt.FlowLayout;

public class TextAreaExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Text Area Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextArea textArea = new JTextArea(5, 20); // Create a JTextArea with 5 rows and 20 columns
        textArea.setLineWrap(true); // Wrap text to the next line if it exceeds the width
        textArea.setWrapStyleWord(true); // Wrap at word boundaries

        JScrollPane scrollPane = new JScrollPane(textArea); // Add a scroll pane for the text area
        frame.add(new JLabel("Your notes:"));
        frame.add(scrollPane); // Add the scroll pane to the frame

        frame.setVisible(true);
    }
}
```

Now you have a multi-line text area with a scrollbar, allowing you to enter more text than fits initially.

### JCheckBox - Toggle Options:
A **JCheckBox** allows the user to select or deselect an option.

**Example**: Let's add a checkbox for agreeing to terms:

```java
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;

public class CheckBoxExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Check Box Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JCheckBox checkBox = new JCheckBox("I agree to the terms and conditions"); // Create a JCheckBox
        frame.add(checkBox);

        frame.setVisible(true);
    }
}
```

You'll see a checkbox that you can click to toggle its state.

### JRadioButton - Mutually Exclusive Choices:
**JRadioButtons** are used when you want the user to select only one option from a group. To ensure mutual exclusivity, you group them using a **ButtonGroup**.

**Example**: Let's allow the user to select their favorite color:

```java
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup; // To group radio buttons
import java.awt.FlowLayout;

public class RadioButtonExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Radio Button Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JRadioButton red = new JRadioButton("Red");
        JRadioButton blue = new JRadioButton("Blue");
        JRadioButton green = new JRadioButton("Green");

        ButtonGroup colorGroup = new ButtonGroup(); // Create a button group
        colorGroup.add(red); // Add radio buttons to the group
        colorGroup.add(blue);
        colorGroup.add(green);

        frame.add(new JLabel("Select your favorite color:"));
        frame.add(red);
        frame.add(blue);
        frame.add(green);

        frame.setVisible(true);
    }
}
```

Now, when you click on one color, the others will automatically deselect, ensuring only one choice is made.

### JComboBox - Dropdown List:
A **JComboBox** provides a dropdown list of items from which the user can select one or more.

**Example**: Let's create a dropdown for selecting a country:

```java
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.FlowLayout;

public class ComboBoxExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Combo Box Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        String[] countries = {"Nepal", "India", "China", "USA", "UK"};
        JComboBox<String> countryComboBox = new JComboBox<>(countries); // Create a JComboBox with items
        frame.add(new JLabel("Select your country:"));
        frame.add(countryComboBox);

        frame.setVisible(true);
    }
}
```

You'll see a dropdown box that, when clicked, reveals a list of countries.

### Layout Managers:
You might have noticed `frame.setLayout(new FlowLayout());` in some of the examples. **Layout Managers** are crucial for arranging components within a container. If you don't specify a layout manager, `JFrame` uses `BorderLayout` by default, which can be tricky for simple arrangements.

* **FlowLayout**: Arranges components in a row, from left to right, and wraps to the next row when the current row is full. It's good for simple arrangements.
* **BorderLayout**: Divides the container into five regions: North, South, East, West, and Center. You can place one component in each region. This is the default for `JFrame`.
* **GridLayout**: Arranges components in a grid (rows and columns). Each component gets an equal amount of space.
* **GridBagLayout**: The most flexible and powerful layout manager, but also the most complex. It allows you to create highly customized layouts by specifying constraints for each component.
* **BoxLayout**: Arranges components in a single row or column.

We'll primarily use `FlowLayout` and occasionally `BorderLayout` for simplicity in our examples, but remember that other powerful layout managers exist for more complex designs.

## 7.3 Event-driven Programming and Event Handling: Mouse Event and Key Event

GUIs are **event-driven**. This means that your program waits for an "**event**" to happen (like a button click, a key press, or a mouse movement), and then it responds to that event. This is different from procedural programming where the program executes instructions sequentially.

### What is an Event?
An event is an action that occurs, usually as a result of user interaction, such as clicking a button, typing text, or moving the mouse. It can also be generated by the system, like a timer event.

**Example**: When you click the "Save" button in a word processor, that's an event. The program then "handles" that event by saving your document.

### Event Handling Mechanism:
The core of event-driven programming in Java involves three main parts:

1.  **Event Source**: The GUI component that generates the event (e.g., a `JButton`, `JTextField`).
2.  **Event Object**: An object that encapsulates information about the event that occurred (e.g., `ActionEvent`, `MouseEvent`, `KeyEvent`).
3.  **Event Listener**: An object that "listens" for a specific type of event and performs an action when that event occurs. Event listeners implement specific interfaces (e.g., `ActionListener`, `MouseListener`, `KeyListener`).

How it works:

1.  You register an event listener with an event source.
2.  When an event occurs on the source, the source creates an event object.
3.  The source then calls the appropriate method in the registered listener, passing the event object as an argument.
4.  The listener's method then contains the code to respond to the event.

### ActionEvent and ActionListener (for Buttons):
The most common event for buttons is an `ActionEvent`. To handle it, you implement the **ActionListener** interface. This interface has a single method: `actionPerformed(ActionEvent e)`.

**Example**: Let's make our "Click Me!" button actually do something – display a message when clicked.

```java
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel; // To display the message
import java.awt.FlowLayout;
import java.awt.event.ActionEvent; // For ActionEvent
import java.awt.event.ActionListener; // For ActionListener

public class ButtonClickExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Click Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel messageLabel = new JLabel("Click the button!");
        frame.add(messageLabel);

        JButton button = new JButton("Click Me!");
        frame.add(button);

        // Register the ActionListener with the button
        button.addActionListener(new ActionListener() { // Anonymous inner class
            @Override
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("Button was clicked!"); // Change the label text
            }
        });

        frame.setVisible(true);
    }
}
```

When you run this and click the "Click Me!" button, the text "Click the button!" will change to "Button was clicked!". This is your first interactive GUI!

### MouseEvent and MouseListener (for Mouse Events):
**MouseEvents** are generated when the mouse is pressed, released, clicked, entered (moved onto a component), or exited (moved off a component). The **MouseListener** interface has methods for each of these actions.

* `mousePressed(MouseEvent e)`: When a mouse button is pressed.
* `mouseReleased(MouseEvent e)`: When a mouse button is released.
* `mouseClicked(MouseEvent e)`: When a mouse button is pressed and released without moving the mouse.
* `mouseEntered(MouseEvent e)`: When the mouse cursor enters the bounds of a component.
* `mouseExited(MouseEvent e)`: When the mouse cursor exits the bounds of a component.

**Example**: Let's make a label change text when the mouse enters and exits it, and show a message when clicked.

```java
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mouse Event Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel mouseSensitiveLabel = new JLabel("Move your mouse over me!");
        frame.add(mouseSensitiveLabel);

        mouseSensitiveLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseSensitiveLabel.setText("Mouse Clicked!");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseSensitiveLabel.setText("Mouse Pressed!");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseSensitiveLabel.setText("Mouse Released!");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseSensitiveLabel.setText("Mouse Entered!");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseSensitiveLabel.setText("Mouse Exited!");
            }
        });

        frame.setVisible(true);
    }
}
```

Run this, and move your mouse over the label, click it, etc., and observe how the label's text changes in response to your mouse actions.

### KeyEvent and KeyListener (for Keyboard Events):
**KeyEvents** are generated when a key is pressed, released, or typed. The **KeyListener** interface has methods for each of these actions.

* `keyPressed(KeyEvent e)`: When a key is pressed down.
* `keyReleased(KeyEvent e)`: When a key is released.
* `keyTyped(KeyEvent e)`: When a Unicode character is generated by a key press (e.g., 'a', 'B', '5'). This is often used for character input.

**Example**: Let's make a text field display which key was pressed.

```java
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Key Event Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField inputField = new JTextField(20);
        frame.add(new JLabel("Type here:"));
        frame.add(inputField);

        JLabel keyStatusLabel = new JLabel("Key status: ");
        frame.add(keyStatusLabel);

        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyStatusLabel.setText("Key Typed: " + e.getKeyChar()); // Get the character typed
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyStatusLabel.setText("Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode())); // Get the key text
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyStatusLabel.setText("Key Released: " + KeyEvent.getKeyText(e.getKeyCode()));
            }
        });

        frame.setVisible(true);
    }
}
```

Run this, click on the text field to give it focus, and then start typing. You'll see the `keyStatusLabel` updating with information about each key event. Notice the difference between `keyTyped` (for actual characters) and `keyPressed`/`keyReleased` (for any key, including control keys like Shift, Ctrl, Alt).

### Adapter Classes:
Implementing an interface means you must implement all its methods, even if you only need one or two. For interfaces with many methods (like `MouseListener` or `KeyListener`), this can be tedious. Java provides **adapter classes** that implement the interface with empty methods. You can extend an adapter class and only override the methods you need.

**Example** (using MouseAdapter):

```java
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter; // Import MouseAdapter
import java.awt.event.MouseEvent;

public class MouseAdapterExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mouse Adapter Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel mouseSensitiveLabel = new JLabel("Hover and Click!");
        frame.add(mouseSensitiveLabel);

        mouseSensitiveLabel.addMouseListener(new MouseAdapter() { // Extend MouseAdapter
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseSensitiveLabel.setText("Mouse Clicked!");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseSensitiveLabel.setText("Mouse Entered!");
            }
        });

        frame.setVisible(true);
    }
}
```

In this example, we only needed to implement `mouseClicked` and `mouseEntered`, making the code cleaner. The other `MouseListener` methods (`mousePressed`, `mouseReleased`, `mouseExited`) are implicitly handled by the empty methods in `MouseAdapter`.

## 7.4 Building Simple Interactive Applications

Now that you understand Swing components and event handling, let's put it all together to build some simple, interactive applications.

### Application 1: Simple Counter
This application will have a label to display a count, and two buttons: "Increment" and "Decrement".

```java
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCounter {
    private int count = 0; // Instance variable to store the count
    private JLabel countLabel; // Instance variable for the label

    public SimpleCounter() {
        JFrame frame = new JFrame("Simple Counter");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        countLabel = new JLabel("Count: " + count); // Initialize the label
        frame.add(countLabel);

        JButton incrementButton = new JButton("Increment");
        frame.add(incrementButton);

        JButton decrementButton = new JButton("Decrement");
        frame.add(decrementButton);

        // Add ActionListener to Increment button
        incrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++; // Increment the count
                countLabel.setText("Count: " + count); // Update the label
            }
        });

        // Add ActionListener to Decrement button
        decrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count--; // Decrement the count
                countLabel.setText("Count: " + count); // Update the label
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleCounter(); // Create an instance of the counter application
    }
}
```

**Explanation**:

* We declare `count` and `countLabel` as instance variables so they can be accessed and modified by the `ActionListeners`.
* The `incrementButton`'s `actionPerformed` method increases `count` and updates `countLabel`.
* The `decrementButton`'s `actionPerformed` method decreases `count` and updates `countLabel`.
* The `main` method simply creates an instance of `SimpleCounter` to start the application.

### Application 2: Basic Login Form
This application will have two text fields (for username and password), a label to display messages, and a "Login" button.

```java
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField; // For password input
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    public LoginForm() {
        JFrame frame = new JFrame("Login Form");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15); // Use JPasswordField for hidden input

        JButton loginButton = new JButton("Login");
        JLabel messageLabel = new JLabel("Please enter your credentials.");

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(messageLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText(); // Get text from username field
                String password = new String(passwordField.getPassword()); // Get password as String

                // Simple validation (for demonstration)
                if (username.equals("user") && password.equals("pass")) {
                    messageLabel.setText("Login Successful!");
                } else {
                    messageLabel.setText("Invalid Username or Password.");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
```

**Explanation**:

* We use `JPasswordField` for the password input, which visually hides the characters typed.
* When the "Login" button is clicked, we retrieve the text from both fields.
* We perform a very basic validation (username "user", password "pass"). In a real application, you'd check against a database or more secure method.
* The `messageLabel` is updated to show success or failure.

### Application 3: Simple Text Editor (with Key Event Demo)
This application will demonstrate reading text from a `JTextArea` and displaying key presses.

```java
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SimpleTextEditor {
    public SimpleTextEditor() {
        JFrame frame = new JFrame("Simple Text Editor");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextArea textArea = new JTextArea(15, 40); // 15 rows, 40 columns
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(new JLabel("Type your text here:"));
        frame.add(scrollPane);

        JLabel keyInfoLabel = new JLabel("Last Key Pressed: None");
        frame.add(keyInfoLabel);

        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Not using this for this example, but useful for character input
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyInfoLabel.setText("Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not using this for this example
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleTextEditor();
    }
}
```

**Explanation**:

* We create a `JTextArea` wrapped in a `JScrollPane` for multi-line input and scrolling.
* A `JLabel` displays information about the last key pressed.
* We attach a `KeyListener` to the `textArea`.
* In `keyPressed`, we update `keyInfoLabel` with the text representation of the key that was pressed.

### Important Considerations for GUI Programming:

* **Swing is Single-Threaded**: All GUI-related operations (creating components, updating them) should be done on the **Event Dispatch Thread (EDT)**. While our simple examples implicitly handle this, for more complex applications, you might need `SwingUtilities.invokeLater()` to ensure thread safety.
* **Choosing the Right Layout Manager**: This is crucial for creating well-organized and responsive GUIs. Experiment with different layout managers (`BorderLayout`, `GridLayout`, `GridBagLayout`) to understand their strengths.
* **Error Handling**: In real-world applications, you'd add error handling (e.g., `try-catch` blocks) around operations that might fail, like file I/O or network communication initiated by a GUI event.
* **Look and Feel**: Swing allows you to change the "look and feel" of your application to match the native operating system or use a cross-platform look. You can set this using `UIManager.setLookAndFeel()`.

### Next Steps:
This unit provides a strong foundation. To further your skills, explore:

* **More Swing Components**: `JList`, `JTable`, `JTree`, `JMenuBar`, `JMenu`, `JMenuItem`, `JDialog`, `JOptionPane` (for pop-up messages).
* **More Event Types**: `ItemEvent` (for checkboxes/radio buttons), `DocumentEvent` (for text field changes), `WindowListener` (for window events).
* **Custom Painting**: Drawing graphics directly on components using `Graphics` objects.
* **Model-View-Controller (MVC) Pattern**: A design pattern commonly used in GUI applications to separate data (model), presentation (view), and user interaction (controller).

By practicing with these examples and exploring further, you'll become proficient in designing and building interactive GUI applications with Java Swing! Good luck!