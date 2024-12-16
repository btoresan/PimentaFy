package gui;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CentralPanel extends JPanel {

    private static final String CONVERT_TABLE_IMG_PATH = "images/conversion-table.png";

    private final JTextArea textBox;

    public CentralPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        // Button panel
        JPanel fileButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fileButtonsPanel.setBackground(Color.WHITE);

        // Text box
        textBox = new JTextArea(30, 5);
        JScrollPane scrollPane = new JScrollPane(textBox);
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textBox.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // Adding scrollPane to the center panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 10, 10);
        add(scrollPane, gbc);

        // Buttons
        SaveButton saveButton = new SaveButton(textBox);
        fileButtonsPanel.add(saveButton);

        PasteButton pasteButton = new PasteButton(textBox);
        fileButtonsPanel.add(pasteButton);

        ImportButton importButton = new ImportButton(textBox);
        fileButtonsPanel.add(importButton);

        // Adding button panel to center panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(fileButtonsPanel, gbc);

        // Conversion Table
        JPanel imagePanel = new ImagePanel(CONVERT_TABLE_IMG_PATH);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 10, 10);
        add(imagePanel, gbc);
    }

    // Getter for textBox
    public JTextArea getTextBox() {
        return textBox;
    }
}

