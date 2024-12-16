import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton {
    private static final String BACK_BUTTON_ICON_PATH = "images/back_icon.png";

    public BackButton(JFrame frame) {

        // Style Back Button
        ImageIcon backIcon = new ImageIcon(BACK_BUTTON_ICON_PATH);
        setIcon(backIcon);
        setBackground(Color.WHITE);
        setBorderPainted(false);
        setToolTipText("Back");
        setFocusPainted(false);

        // Açtion Back Button
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CurrentScreen.setCurrentScreen("menu");
            }
        });
    }
}
