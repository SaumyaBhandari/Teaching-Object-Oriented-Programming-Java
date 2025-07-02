import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverterGUI extends JFrame {

    private JTextField amountTextField;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JLabel resultLabel;
    private JButton convertButton;

    private static final double USD_TO_EUR = 0.92;
    private static final double USD_TO_ZWL = 67355.6;
    private static final double USD_TO_NPR = 137.00;

    public CurrencyConverterGUI() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the window

        // Use GridBagLayout for more flexible positioning
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components

        // --- Amount Input ---
        JLabel amountLabel = new JLabel("Amount:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(amountLabel, gbc);

        amountTextField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(amountTextField, gbc);

        // --- From Currency Dropdown ---
        JLabel fromLabel = new JLabel("From:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(fromLabel, gbc);

        String[] currencies = {"USD", "EUR", "ZWL", "NPR"};
        fromCurrencyComboBox = new JComboBox<>(currencies);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(fromCurrencyComboBox, gbc);

        // --- To Currency Dropdown ---
        JLabel toLabel = new JLabel("To:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(toLabel, gbc);

        toCurrencyComboBox = new JComboBox<>(currencies);
        toCurrencyComboBox.setSelectedItem("NPR"); // Default selection
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(toCurrencyComboBox, gbc);

        // --- Convert Button ---
        convertButton = new JButton("Convert");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        add(convertButton, gbc);

        // --- Result Label ---
        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Serif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(resultLabel, gbc);

        // --- Add Action Listener to Button ---
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
            String toCurrency = (String) toCurrencyComboBox.getSelectedItem();

            double convertedAmount = performConversion(amount, fromCurrency, toCurrency);

            DecimalFormat df = new DecimalFormat("#.##"); // Format to two decimal places
            resultLabel.setText("Result: " + df.format(convertedAmount) + " " + toCurrency);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // This is where you would integrate with a real currency exchange API
    // For this example, we use fixed hypothetical rates
    private double performConversion(double amount, String fromCurrency, String toCurrency) {
        double amountInUSD = 0;

        // Convert 'from' currency to USD (our hypothetical base)
        switch (fromCurrency) {
            case "USD":
                amountInUSD = amount;
                break;
            case "EUR":
                amountInUSD = amount / USD_TO_EUR; // EUR to USD
                break;
            case "ZWL":
                amountInUSD = amount / USD_TO_ZWL; // ZWL to USD
                break;
            case "NPR":
                amountInUSD = amount / USD_TO_NPR; // NPR to USD
                break;
            default:
                throw new IllegalArgumentException("Unsupported 'from' currency: " + fromCurrency);
        }

        // Convert from USD to 'to' currency
        double convertedAmount = 0;
        switch (toCurrency) {
            case "USD":
                convertedAmount = amountInUSD;
                break;
            case "EUR":
                convertedAmount = amountInUSD * USD_TO_EUR; // USD to EUR
                break;
            case "ZWL":
                convertedAmount = amountInUSD * USD_TO_ZWL; // USD to ZWL
                break;
            case "NPR":
                convertedAmount = amountInUSD * USD_TO_NPR; // USD to NPR
                break;
            default:
                throw new IllegalArgumentException("Unsupported 'to' currency: " + toCurrency);
        }
        return convertedAmount;
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverterGUI().setVisible(true);
            }
        });
    }
}