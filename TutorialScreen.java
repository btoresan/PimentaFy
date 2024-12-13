import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TutorialScreen {

    private static final int WINDOW_WIDTH ;
    private static final int WINDOW_HEIGHT;
    private static final String SMALL_PEPPERFY_ICON_PATH = "images/small_pepperfy_icon.png";
    private static final String BACK_BUTTON_ICON_PATH = "images/back_icon.png";
    private static final String TUTORIAL_SCREEN_IMG_PATH = "images/tutorial-screen.png";

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WINDOW_WIDTH = screenSize.width;
        WINDOW_HEIGHT = screenSize.height;
    }

    public TutorialScreen() {

        // Window
        JFrame tutorialFrame = new JFrame();
        tutorialFrame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        tutorialFrame.setTitle("CONVERT");
        tutorialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tutorialFrame.setResizable(true);
        tutorialFrame.setLocationRelativeTo(null);
        tutorialFrame.setLayout(null);
        tutorialFrame.getContentPane().setBackground(Color.WHITE);

        // Layout manager window
        tutorialFrame.setLayout(new BorderLayout());

        // Layout manager panels
        JPanel centralTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centralTopPanel.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JPanel centralPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcCentralPanel = new GridBagConstraints();
        centralPanel.setBackground(Color.WHITE);

        ImagePanel imagePanel = new ImagePanel(TUTORIAL_SCREEN_IMG_PATH);

        // -------------------- TOP PANEL --------------------
        // Title label
        JLabel Title = new JLabel();
        Title.setText("<html><span style='color:red;'>PEPPER</span><span style='color:green;'>.FY</span></html>");									// Change text color to red
        Title.setFont(new Font("Arial", Font.BOLD, 35));
        centralTopPanel.add(Title);

        // Pepper.Fy icon
        ImageIcon pepperfyIcon = new ImageIcon(SMALL_PEPPERFY_ICON_PATH);
        JLabel pepperfyLabel = new JLabel(pepperfyIcon);
        centralTopPanel.add(pepperfyLabel);
        topPanel.add(centralTopPanel,BorderLayout.CENTER);

        // Style backButton
        ImageIcon back_icon = new ImageIcon(BACK_BUTTON_ICON_PATH);
        JButton backButton = new JButton(back_icon);
        backButton.setBackground(Color.WHITE);
        backButton.setBorderPainted(false);
        backButton.setToolTipText("Back");
        backButton.setFocusPainted(false);

        topPanel.add(backButton,BorderLayout.WEST);

        // Action backButton
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                tutorialFrame.dispose();										// close options window
                CurrentScreen.setCurrentScreen("menu");						// open menu screen
            }
        });

        // -------------------- CENTRAL PANEL --------------------
        // Tutorial screen image
        gbcCentralPanel.gridx = 1;
        gbcCentralPanel.gridy = 1;
        gbcCentralPanel.gridwidth = 1;
        gbcCentralPanel.weightx = 1.0;
        gbcCentralPanel.weighty = 1.0;
        gbcCentralPanel.fill = GridBagConstraints.BOTH;
        gbcCentralPanel.insets = new Insets(70, 70, 70, 70);
        centralPanel.add(imagePanel,gbcCentralPanel);


        // Adding the created panels to the window
        tutorialFrame.add(topPanel,BorderLayout.NORTH);
        tutorialFrame.add(centralPanel,BorderLayout.CENTER);

        tutorialFrame.setVisible(true);
    }
}
