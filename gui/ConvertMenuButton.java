package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConvertMenuButton extends JButton {

    public ConvertMenuButton(JFrame menuFrame) {

        // Style ConvertMenuButton
        setText("Convert");
        setFont(new Font("Arial", Font.ROMAN_BASELINE, 50));
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setFocusPainted(false);

        // Action ConvertMenuButton
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                CurrentScreen.setCurrentScreen("convert");
            }
        });
    }
}
