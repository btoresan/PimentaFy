import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen {

    private static final int WINDOW_WIDTH ;
    private static final int WINDOW_HEIGHT;
    private static final String PEPPERFY_ICON_PATH = "images/pepperfy_icon.png";

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WINDOW_WIDTH = screenSize.width;
        WINDOW_HEIGHT = screenSize.height;
    }

    public MenuScreen() {

        // Window
        JFrame menuFrame = new JFrame();
        menuFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        menuFrame.setTitle("MENU");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setResizable(true);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.getContentPane().setBackground(Color.WHITE);

        // Layout manager
        menuFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbcMenuFrame = new GridBagConstraints();

        // Label (Title)
        JLabel Title = new JLabel();
        Title.setText("<html><span style='color:red;'>PEPPER</span><span style='color:green;'>.FY</span></html>");
        Title.setFont(new Font("Arial", Font.BOLD, 80));
        gbcMenuFrame.gridx = 0;
        gbcMenuFrame.gridy = 0;
        gbcMenuFrame.gridwidth = 2;
        menuFrame.add(Title, gbcMenuFrame);

        // Pepper.Fy icon
        ImageIcon pepperfyIcon = new ImageIcon(PEPPERFY_ICON_PATH);
        JLabel pepperfyLabel = new JLabel(pepperfyIcon);
        gbcMenuFrame.gridx =2;
        gbcMenuFrame.gridy = 0;
        gbcMenuFrame.gridwidth = 1;
        menuFrame.add(pepperfyLabel, gbcMenuFrame);

        // Button: Convert
        JButton convertButton = new JButton();
        convertButton.setText("Convert");
        convertButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 50));
        convertButton.setBackground(Color.WHITE);
        convertButton.setForeground(Color.BLACK);
        convertButton.setFocusPainted(false);
        gbcMenuFrame.gridx = 0;
        gbcMenuFrame.gridy = 1;
        gbcMenuFrame.gridwidth = 3;
        gbcMenuFrame.fill = GridBagConstraints.HORIZONTAL;
        gbcMenuFrame.insets = new java.awt.Insets(20, 0, 0, 0);
        menuFrame.add(convertButton, gbcMenuFrame);

        // Action convertButton
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                CurrentScreen.setCurrentScreen("convert");
            }
        });

        // Button: Tutorial
        JButton tutorialButton = new JButton();
        tutorialButton.setText("Tutorial");
        tutorialButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 50));
        tutorialButton.setBackground(Color.WHITE);
        tutorialButton.setForeground(Color.BLACK);
        gbcMenuFrame.gridy = 2;
        menuFrame.add(tutorialButton, gbcMenuFrame);

        // Action tutorialButton
        tutorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                CurrentScreen.setCurrentScreen("tutorial");
            }
        });

        // Button: Exit
        JButton exitButton = new JButton();
        exitButton.setText("Exit");
        exitButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 50));
        exitButton.setBackground(Color.WHITE);
        exitButton.setForeground(Color.BLACK);
        gbcMenuFrame.gridy = 3;
        menuFrame.add(exitButton, gbcMenuFrame);

        // Action exitButton
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?", "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });


        menuFrame.setVisible(true);
    }
}

