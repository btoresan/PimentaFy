import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TutorialScreen {

    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 768;
    private static final String SMALL_PEPPERFY_ICON_PATH = "images/small_pepperfy_icon.png";
    private static final String BACK_BUTTON_ICON_PATH = "images/back_icon.png";

    public TutorialScreen() {
        JFrame tutorialFrame = new JFrame();
        JLabel Title = new JLabel();

        // Window
        tutorialFrame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        tutorialFrame.setTitle("TUTORIAL");
        tutorialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tutorialFrame.setResizable(false);
        tutorialFrame.setLocationRelativeTo(null);
        tutorialFrame.setLayout(null);
        tutorialFrame.getContentPane().setBackground(Color.WHITE);


        // Label
        tutorialFrame.add(Title);
        Title.setText("<html><span style='color:red;'>PEPPER</span><span style='color:green;'>.FY</span></html>");									// Change text color to red
        Title.setFont(new Font("Arial", Font.BOLD, 35));
        Title.setBounds(400, 10, 300, 60);

        // Pepper.Fy icon
        ImageIcon pepperfyIcon = new ImageIcon(SMALL_PEPPERFY_ICON_PATH);
        JLabel pepperfyLabel = new JLabel(pepperfyIcon);
        pepperfyLabel.setBounds(595, 10, 30, 60);
        tutorialFrame.add(pepperfyLabel);


        // Style backButton
        ImageIcon back_icon = new ImageIcon(BACK_BUTTON_ICON_PATH);
        JButton backButton = new JButton(back_icon);
        tutorialFrame.add(backButton);
        backButton.setBounds(40, 15, 50, 50);
        backButton.setBackground(Color.WHITE);
        backButton.setBorderPainted(false);
        backButton.setToolTipText("Back");

        // Action backButton
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                tutorialFrame.dispose();										// close options window
                CurrentScreen.setCurrentScreen("menu");						// open menu screen
            }
        });

        tutorialFrame.setVisible(true);
    }
}
