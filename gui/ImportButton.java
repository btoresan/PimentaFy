package gui;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class ImportButton extends JButton {
    private static final String IMPORT_BUTTON_ICON_PATH = "images/import_icon.png";

    public ImportButton(JTextArea textBox) {

        // Style ImportButton
        ImageIcon importIcon = new ImageIcon(IMPORT_BUTTON_ICON_PATH);
        setIcon(importIcon);
        setBackground(Color.WHITE);
        setBorderPainted(false);
        setToolTipText("Import");

        // Action ImportButton
        addActionListener(e -> {
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
    }
}
