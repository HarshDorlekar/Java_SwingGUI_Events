package gui_swing_events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SwingEventWindow extends JFrame {

    private JTextField inputField;
    private JTextArea outputArea;
    private JRadioButton totalButton;
    private JRadioButton averageButton;
    private JRadioButton maximumButton;
    private JRadioButton minimumButton;
    private JButton calculateButton;
    private ButtonGroup operationButtonGroup;

    public SwingEventWindow() {
        // Initialize the JFrame and set its properties
        setTitle("Swing Event Window");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set the layout manager
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel inputLabel = new JLabel("Enter a string of numbers separated by commas:");
        inputLabel.setHorizontalAlignment(SwingConstants.LEFT);
        inputField = new JTextField(20);
        inputField.setPreferredSize(new Dimension(inputField.getPreferredSize().width, 100)); // Increase the height to 100 pixels

        Font inputFont = inputField.getFont(); // Get the current font
        Font newFont = inputFont.deriveFont(Font.BOLD, 16); // Create a new font with increased size
        inputField.setFont(newFont); // Set the new font
        inputField.setHorizontalAlignment(JTextField.CENTER); // Center-align the text in the input field

        outputArea = new JTextArea(10, 20);
        Font outputFont = outputArea.getFont(); // Get the current font
        Font newOutputFont = outputFont.deriveFont(Font.BOLD, 16); // Create a new font with increased size
        outputArea.setFont(newOutputFont); // Set the new fon
        outputArea.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align the text in the output area




        totalButton = new JRadioButton("Total");
        averageButton = new JRadioButton("Average");
        maximumButton = new JRadioButton("Maximum");
        minimumButton = new JRadioButton("Minimum");
        calculateButton = new JButton("Calculate");

        // Create a button group to ensure only one radio button can be selected at a time
        operationButtonGroup = new ButtonGroup();
        operationButtonGroup.add(totalButton);
        operationButtonGroup.add(averageButton);
        operationButtonGroup.add(maximumButton);
        operationButtonGroup.add(minimumButton);

        // Create a panel for radio buttons
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        radioButtonPanel.add(totalButton);
        radioButtonPanel.add(averageButton);
        radioButtonPanel.add(maximumButton);
        radioButtonPanel.add(minimumButton);

        // Add components to the main panel
        mainPanel.add(inputLabel);
        mainPanel.add(inputField);
        mainPanel.add(radioButtonPanel);
        mainPanel.add(calculateButton);
        mainPanel.add(outputArea);

        // Add event listener to the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        // Set the main panel as the content pane
        setContentPane(mainPanel);

        // Make the window visible
        setVisible(true);
    }

    private ArrayList<Double> parseInput(String input) {
        ArrayList<Double> numbers = new ArrayList<>();
        String[] numberTokens = input.split(",");
        try {
            for (String numberToken : numberTokens) {
                double number = Double.parseDouble(numberToken.trim());
                numbers.add(number);
            }
            return numbers;
        } catch (NumberFormatException e) {
            return null; // Return null if any number fails to parse
        }
    }

    private void calculate() {
        String input = inputField.getText();
        ArrayList<Double> numbers = parseInput(input);
        if (numbers != null) {
            if (totalButton.isSelected()) {
                double total = Excel.calculateTotal(numbers);
                outputArea.setText("Total: " + total);
            } else if (averageButton.isSelected()) {
                double average = Excel.calculateAverage(numbers);
                outputArea.setText("Average: " + average);
            } else if (maximumButton.isSelected()) {
                double maximum = Excel.calculateMaximum(numbers);
                outputArea.setText("Maximum: " + maximum);
            } else if (minimumButton.isSelected()) {
                double minimum = Excel.calculateMinimum(numbers);
                outputArea.setText("Minimum: " + minimum);
            }
        } else {
            outputArea.setText("Invalid input. Please enter a valid string of numbers.");
        }
    }
}
