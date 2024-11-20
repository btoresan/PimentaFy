import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;

public class MenuScreen {

    public MenuScreen() {
        JFrame menuFrame = new JFrame();
        JButton convertButton = new JButton();
        JButton tutorialButton = new JButton();
        JButton exitButton = new JButton();

        JLabel Logo = new JLabel();

        // Window
        menuFrame.setVisible(true);											// leave the window visible
        menuFrame.setSize(800,500);											// width x height
        menuFrame.setTitle("MENU");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// stop running when pressed the close button
        menuFrame.setResizable(false);											// does not allow changing the window size
        menuFrame.setLocationRelativeTo(null);									// the window open in the middle of the screen
        menuFrame.setLayout(null);


        // Style covertButton
        menuFrame.add(convertButton);											// add the button in the window
        convertButton.setText("Convert");
        convertButton.setBounds(265, 180, 250, 70);							// set the distance from x, y and the width, height
        convertButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 30));	// set the font, the style font and the size of the text in the button
        convertButton.setBackground(Color.WHITE);							// set the color of the button (red,blue,green)
        convertButton.setForeground(Color.BLACK);							// set the color of the font (red,blue,green)


        // Action convertButton
        convertButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                menuFrame.dispose();										// close menu
                CurrentScreen.setCurrentScreen("convert");						// open convert screen
            }
        });



        // Style tutorialButton
        menuFrame.add(tutorialButton);											// add the button in the window
        tutorialButton.setText("Tutorial");
        tutorialButton.setBounds(265, 270, 250, 70);							// set the distance from x, y and the width, height
        tutorialButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 30));	// set the font, the style font and the size of the text in the button
        tutorialButton.setBackground(Color.WHITE);							// set the color of the button (red,blue,green)
        tutorialButton.setForeground(Color.BLACK);							// set the color of the font (red,blue,green)

        // Action tutorialButton
        tutorialButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                menuFrame.dispose();										// close menu
                CurrentScreen.setCurrentScreen("tutorial");						// open options screen
            }
        });



        // Style exitButton
        menuFrame.add(exitButton);												// add the button in the window
        exitButton.setText("Exit");
        exitButton.setBounds(265, 360, 250, 70);							// set the distance from x, y and the width, height
        exitButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 30));		// set the font, the style font and the size of the text in the button
        exitButton.setBackground(Color.WHITE);								// set the color of the button (red,blue,green)
        exitButton.setForeground(Color.BLACK);								// set the color of the font (red,blue,green)

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


        // Label
        menuFrame.add(Logo);
        Logo.setText("<html><span style='color:red;'>PEPPER</span><span style='color:green;'>.FY</span></html>");
        Logo.setFont(new Font("Arial", Font.BOLD, 60));
        Logo.setBounds(235, 70, 350, 50);




    }

}
