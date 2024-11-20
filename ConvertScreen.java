import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class ConvertScreen {

    public ConvertScreen() {
        JFrame convertFrame = new JFrame();
        JLabel Logo = new JLabel();
        JTextArea textBox = new JTextArea(2,20);


        // Window
        convertFrame.setVisible(true);																// leave the window visible
        convertFrame.setSize(800,500);																// width x height
        convertFrame.setTitle("CONVERT");
        convertFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);								// stop running when pressed the close button
        convertFrame.setResizable(false);															// does not allow changing the window size
        convertFrame.setLocationRelativeTo(null);													// the window open in the middle of the screen
        convertFrame.setLayout(null);

        // Configure a scroll for the textBox
        JScrollPane scrollPane = new JScrollPane(textBox);
        scrollPane.setBounds(50, 100, 300, 320); 													// set position of scrollPane
        convertFrame.add(scrollPane);


        // Text box
        Border border = BorderFactory.createLineBorder(Color.BLACK);								// creates a border for the textBox
        textBox.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);


        // Style backButton
        ImageIcon back_icon = new ImageIcon("C:/Users/gleal/IdeaProjects/interface_test/images/back_icon.png");
        JButton backButton = new JButton(back_icon);
        convertFrame.add(backButton);
        backButton.setBounds(25, 15, 30, 30);
        backButton.setBackground(Color.WHITE);
        backButton.setBorderPainted(false);
        backButton.setToolTipText("Back");

        // Action backButton
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                convertFrame.dispose();										// close convert window
                CurrentScreen.setCurrentScreen("menu");						// open menu screen
            }
        });


        // Style saveButton
        ImageIcon save_icon = new ImageIcon("C:/Users/gleal/IdeaProjects/interface_test/images/save_icon.png");
        JButton saveButton = new JButton(save_icon);
        convertFrame.add(saveButton);
        saveButton.setBounds(65, 70, 25, 25);
        saveButton.setBackground(Color.WHITE);
        saveButton.setBorderPainted(false);
        saveButton.setToolTipText("Save");

        // Action saveButton


        // Style pasteButton
        ImageIcon paste_icon = new ImageIcon("C:/Users/gleal/IdeaProjects/interface_test/images/paste_icon.png");
        JButton pasteButton = new JButton(paste_icon);
        convertFrame.add(pasteButton);
        pasteButton.setBounds(100, 68, 30, 30);
        pasteButton.setBackground(Color.WHITE);
        pasteButton.setBorderPainted(false);
        pasteButton.setToolTipText("Paste");

        // Action pasteButton


        // Style importButton
        ImageIcon import_icon = new ImageIcon("C:/Users/gleal/IdeaProjects/interface_test/images/import_icon.png");
        JButton importButton = new JButton(import_icon);
        convertFrame.add(importButton);
        importButton.setBounds(140, 68, 22, 28);
        importButton.setBackground(Color.WHITE);
        importButton.setBorderPainted(false);
        importButton.setToolTipText("Import");

        // Action importButton


        // Label
        convertFrame.add(Logo);
        Logo.setText("<html><span style='color:red;'>PEPPER</span><span style='color:green;'>.FY</span></html>");									// Change text color to red
        Logo.setFont(new Font("Arial", Font.BOLD, 25));
        Logo.setBounds(310, 10, 350, 50);


    }
}
