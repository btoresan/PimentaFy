import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TutorialScreen {

    public TutorialScreen() {
        JFrame tutorialFrame = new JFrame();
        JLabel Logo = new JLabel();

        // Window
        tutorialFrame.setVisible(true);																// leave the window visible
        tutorialFrame.setSize(800,500);																// width x height
        tutorialFrame.setTitle("TUTORIAL");
        tutorialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);								// stop running when pressed the close button
        tutorialFrame.setResizable(false);															// does not allow changing the window size
        tutorialFrame.setLocationRelativeTo(null);													// the window open in the middle of the screen
        tutorialFrame.setLayout(null);



        // Style backButton

        ImageIcon back_icon = new ImageIcon("C:/Users/gleal/IdeaProjects/interface_test/images/back_icon.png");
        JButton backButton = new JButton(back_icon);
        tutorialFrame.add(backButton);
        backButton.setBounds(25, 15, 30, 30);
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



        // Label
        tutorialFrame.add(Logo);
        Logo.setText("<html><span style='color:red;'>PEPPER</span><span style='color:green;'>.FY</span></html>");									// Change text color to red
        Logo.setFont(new Font("Arial", Font.BOLD, 25));
        Logo.setBounds(310, 10, 350, 50);
    }

}
