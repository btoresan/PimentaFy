package gui;
import java.awt.*;
import javax.swing.*;

public class TutorialScreen {

    private static final int WINDOW_WIDTH ;
    private static final int WINDOW_HEIGHT;
    private static final String TUTORIAL_SCREEN_IMG_PATH = "images/tutorial-screen.png";

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WINDOW_WIDTH = screenSize.width-100;
        WINDOW_HEIGHT = screenSize.height-100;
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

        // Panels for the tutorial frame
        TopPanel topPanel = new TopPanel(tutorialFrame);

        JPanel centralPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcCentralPanel = new GridBagConstraints();
        centralPanel.setBackground(Color.WHITE);

        ImagePanel imagePanel = new ImagePanel(TUTORIAL_SCREEN_IMG_PATH);

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
