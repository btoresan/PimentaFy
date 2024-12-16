import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveButton extends JButton {
    private static final String SAVE_BUTTON_ICON_PATH = "images/save_icon.png";

    public SaveButton(JTextArea textBox) {

        // Style SaveButton
        ImageIcon saveIcon = new ImageIcon(SAVE_BUTTON_ICON_PATH);
        setIcon(saveIcon);
        setBackground(Color.WHITE);
        setBorderPainted(false);
        setToolTipText("Save");

        // Action SaveButton
        addActionListener(e -> {
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
    }
}
