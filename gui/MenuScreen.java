package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

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
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        // Buttons
        ConvertMenuButton convertMenuButton = new ConvertMenuButton(menuFrame);
        gbcMenuFrame.gridx = 0;
        gbcMenuFrame.gridy = 1;
        gbcMenuFrame.gridwidth = 3;
        gbcMenuFrame.fill = GridBagConstraints.HORIZONTAL;
        gbcMenuFrame.insets = new java.awt.Insets(20, 0, 0, 0);
        menuFrame.add(convertMenuButton, gbcMenuFrame);

        TutorialMenuButton tutorialMenuButton = new TutorialMenuButton(menuFrame);
        gbcMenuFrame.gridy = 2;
        menuFrame.add(tutorialMenuButton, gbcMenuFrame);

        ExitMenuButton exitMenuButton = new ExitMenuButton(menuFrame);
        gbcMenuFrame.gridy = 3;
        menuFrame.add(exitMenuButton, gbcMenuFrame);

        menuFrame.setVisible(true);
    }
}

