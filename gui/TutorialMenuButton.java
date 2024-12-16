package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutorialMenuButton extends JButton {

    public TutorialMenuButton(JFrame menuFrame) {

        // Style TutorialMenuButton
        setText("Tutorial");
        setFont(new Font("Arial", Font.ROMAN_BASELINE, 50));
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);

        // Action TutorialMenuButton
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                CurrentScreen.setCurrentScreen("tutorial");
            }
        });
    }
}
