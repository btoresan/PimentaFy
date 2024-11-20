import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;

public class ConvertScreen {

    private static final String BACK_BUTTON_ICON_PATH = "C:\\Users\\gleal\\OneDrive\\Área de Trabalho\\PepperFy\\images\\back_icon.png";
    private static final String SAVE_BUTTON_ICON_PATH = "C:\\Users\\gleal\\OneDrive\\Área de Trabalho\\PepperFy\\images\\save_icon.png";
    private static final String PASTE_BUTTON_ICON_PATH = "C:\\Users\\gleal\\OneDrive\\Área de Trabalho\\PepperFy\\images\\paste_icon.png";
    private static final String IMPORT_BUTTON_ICON_PATH = "C:\\Users\\gleal\\OneDrive\\Área de Trabalho\\PepperFy\\images\\import_icon.png";

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
        ImageIcon back_icon = new ImageIcon(BACK_BUTTON_ICON_PATH);
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
        ImageIcon save_icon = new ImageIcon(SAVE_BUTTON_ICON_PATH);
        JButton saveButton = new JButton(save_icon);
        convertFrame.add(saveButton);
        saveButton.setBounds(65, 70, 25, 25);
        saveButton.setBackground(Color.WHITE);
        saveButton.setBorderPainted(false);
        saveButton.setToolTipText("Save");

        // Action saveButton
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create a JFileChooser to save the file
                JFileChooser saveChooser = new JFileChooser();
                saveChooser.setDialogTitle("Save as");

                // Configure the filter to save only text files
                saveChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files (*.txt)", "txt"));

                // Display the save file dialog
                int userSelection = saveChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {

                    // Retrieve selected file path
                    File fileToSave = saveChooser.getSelectedFile();

                    if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
                        fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
                    }

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {

                        // Write the text from the textBox to the file
                        writer.write(textBox.getText());
                        writer.flush();
                        JOptionPane.showMessageDialog(null, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });



        // Style pasteButton
        ImageIcon paste_icon = new ImageIcon(PASTE_BUTTON_ICON_PATH);
        JButton pasteButton = new JButton(paste_icon);
        convertFrame.add(pasteButton);
        pasteButton.setBounds(100, 68, 30, 30);
        pasteButton.setBackground(Color.WHITE);
        pasteButton.setBorderPainted(false);
        pasteButton.setToolTipText("Paste");

        // Action pasteButton
        pasteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Access the content of the transfer area
                    String clipboardText = (String) Toolkit.getDefaultToolkit()
                            .getSystemClipboard()
                            .getData(DataFlavor.stringFlavor);

                    // Add the text in the textBox
                    textBox.append(clipboardText);

                } catch (Exception ex) {
                    // Handle possible errors (e.g. clipboard content is not text)
                    JOptionPane.showMessageDialog(null,
                            "Unable to paste text from clipboard.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        // Style importButton
        ImageIcon import_icon = new ImageIcon(IMPORT_BUTTON_ICON_PATH);
        JButton importButton = new JButton(import_icon);
        convertFrame.add(importButton);
        importButton.setBounds(140, 68, 22, 28);
        importButton.setBackground(Color.WHITE);
        importButton.setBorderPainted(false);
        importButton.setToolTipText("Import");

        // Action importButton
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create a JFileChooser to open the file
                JFileChooser importChooser = new JFileChooser();
                importChooser.setDialogTitle("Open File");

                // Configure the filter to show only text files
                importChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files (*.txt)", "txt"));

                // Display the select file dialog
                int userSelection = importChooser.showOpenDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    // Retrieve selected file path
                    File fileToOpen = importChooser.getSelectedFile();

                    try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {
                        // Clean textBox
                        textBox.setText("");

                        // Read the content of the file line by line and add it to the textBox
                        String line;
                        while ((line = reader.readLine()) != null) {
                            textBox.append(line + "\n");
                        }
                        JOptionPane.showMessageDialog(null, "File imported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error importing the file.", "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });


        // Label
        convertFrame.add(Logo);
        Logo.setText("<html><span style='color:red;'>PEPPER</span><span style='color:green;'>.FY</span></html>");									// Change text color to red
        Logo.setFont(new Font("Arial", Font.BOLD, 25));
        Logo.setBounds(310, 10, 350, 50);


    }
}
