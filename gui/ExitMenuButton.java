package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitMenuButton extends JButton {

    public ExitMenuButton(JFrame menuFrame) {

        // Style ExitMenuButton
        setText("Exit");
        setFont(new Font("Arial", Font.ROMAN_BASELINE, 50));
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);

        // Action ExitMenuButton
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?", "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}
