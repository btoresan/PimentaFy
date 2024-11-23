import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;

public class MenuScreen {

    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 768;
    private static final String PEPPERFY_ICON_PATH = "images/pepperfy_icon.png";

    public MenuScreen() {
        JFrame menuFrame = new JFrame();
        JButton convertButton = new JButton();
        JButton tutorialButton = new JButton();
        JButton exitButton = new JButton();
        JLabel Title = new JLabel();

        // Window
        menuFrame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        menuFrame.setTitle("MENU");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setLayout(null);
        menuFrame.getContentPane().setBackground(Color.WHITE);

        // Label
        Title.setText("<html><span style='color:red;'>PEPPER</span><span style='color:green;'>.FY</span></html>");
        Title.setFont(new Font("Arial", Font.BOLD, 80));
        Title.setBounds(270, 130, 470, 65);
        menuFrame.add(Title);

        // Pepper.Fy icon
        ImageIcon pepperfyIcon = new ImageIcon(PEPPERFY_ICON_PATH);
        JLabel pepperfyLabel = new JLabel(pepperfyIcon);
        pepperfyLabel.setBounds(655, 60, pepperfyIcon.getIconWidth(), pepperfyIcon.getIconHeight());
        menuFrame.add(pepperfyLabel);


        // Style covertButton
        convertButton.setText("Convert");
        convertButton.setBounds(355, 280, 300, 60);
        convertButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 50));
        convertButton.setBackground(Color.WHITE);
        convertButton.setForeground(Color.BLACK);
        convertButton.setFocusPainted(false);
        menuFrame.add(convertButton);

        // Action convertButton
        convertButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                menuFrame.dispose();
                CurrentScreen.setCurrentScreen("convert");
            }
        });



        // Style tutorialButton
        tutorialButton.setText("Tutorial");
        tutorialButton.setBounds(355, 380, 300, 60);
        tutorialButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 50));
        tutorialButton.setBackground(Color.WHITE);
        tutorialButton.setForeground(Color.BLACK);
        menuFrame.add(tutorialButton);

        // Action tutorialButton
        tutorialButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                menuFrame.dispose();
                CurrentScreen.setCurrentScreen("tutorial");
            }
        });


        // Style exitButton
        menuFrame.add(exitButton);
        exitButton.setText("Exit");
        exitButton.setBounds(355, 480, 300, 60);
        exitButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 50));
        exitButton.setBackground(Color.WHITE);
        exitButton.setForeground(Color.BLACK);

        // Action exitButton Button
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                int answer = JOptionPane.showConfirmDialog(null,"Are you sure you want to leave?","Confirmation",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);

                if (answer == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }

        });

        menuFrame.setVisible(true);

    }

}
