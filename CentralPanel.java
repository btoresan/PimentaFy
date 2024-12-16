import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CentralPanel extends JPanel {

    private static final String SAVE_BUTTON_ICON_PATH = "images/save_icon.png";
    private static final String PASTE_BUTTON_ICON_PATH = "images/paste_icon.png";
    private static final String IMPORT_BUTTON_ICON_PATH = "images/import_icon.png";
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

        // Style saveButton
        ImageIcon saveIcon = new ImageIcon(SAVE_BUTTON_ICON_PATH);
        JButton saveButton = new JButton(saveIcon);
        saveButton.setBackground(Color.WHITE);
        saveButton.setBorderPainted(false);
        saveButton.setToolTipText("Save");
        fileButtonsPanel.add(saveButton);

        // Action saveButton
        saveButton.addActionListener(e -> {
            JFileChooser saveChooser = new JFileChooser();
            saveChooser.setDialogTitle("Save as");
            saveChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));

            int userSelection = saveChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = saveChooser.getSelectedFile();
                if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                    writer.write(textBox.getText());
                    writer.flush();
                    JOptionPane.showMessageDialog(null, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Style pasteButton
        ImageIcon pasteIcon = new ImageIcon(PASTE_BUTTON_ICON_PATH);
        JButton pasteButton = new JButton(pasteIcon);
        pasteButton.setBackground(Color.WHITE);
        pasteButton.setBorderPainted(false);
        pasteButton.setToolTipText("Paste");
        fileButtonsPanel.add(pasteButton);

        // Action pasteButton
        pasteButton.addActionListener(e -> {
            try {
                String clipboardText = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard()
                        .getData(DataFlavor.stringFlavor);
                textBox.append(clipboardText);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Unable to paste text from clipboard.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Style importButton
        ImageIcon importIcon = new ImageIcon(IMPORT_BUTTON_ICON_PATH);
        JButton importButton = new JButton(importIcon);
        importButton.setBackground(Color.WHITE);
        importButton.setBorderPainted(false);
        importButton.setToolTipText("Import");
        fileButtonsPanel.add(importButton);

        // Action importButton
        importButton.addActionListener(e -> {
            JFileChooser importChooser = new JFileChooser();
            importChooser.setDialogTitle("Open File");
            importChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));

            int userSelection = importChooser.showOpenDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = importChooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {
                    textBox.setText("");
                    String line;
                    while ((line = reader.readLine()) != null) {
                        textBox.append(line + "\n");
                    }
                    JOptionPane.showMessageDialog(null, "File imported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error importing the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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

