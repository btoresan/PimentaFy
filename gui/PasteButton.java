package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class PasteButton extends JButton {
    private static final String PASTE_BUTTON_ICON_PATH = "images/paste_icon.png";

    public PasteButton(JTextArea textBox) {

        // Style PasteButton
        ImageIcon pasteIcon = new ImageIcon(PASTE_BUTTON_ICON_PATH);
        setIcon(pasteIcon);
        setBackground(Color.WHITE);
        setBorderPainted(false);
        setToolTipText("Paste");

        // Action PasteButton
        addActionListener(e -> {
            try {
                String clipboardText = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard()
                        .getData(DataFlavor.stringFlavor);

                textBox.append(clipboardText);
            } catch (UnsupportedFlavorException | IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Unable to paste text from clipboard.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
