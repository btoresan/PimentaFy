import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConvertScreen {

    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 768;
    private static final String SMALL_PEPPERFY_ICON_PATH = "images/small_pepperfy_icon.png";
    private static final String BACK_BUTTON_ICON_PATH = "images/back_icon.png";
    private static final String SAVE_BUTTON_ICON_PATH = "images/save_icon.png";
    private static final String PASTE_BUTTON_ICON_PATH = "images/paste_icon.png";
    private static final String IMPORT_BUTTON_ICON_PATH = "images/import_icon.png";
    private static final String PLAY_BUTTON_ICON_PATH = "images/play_icon.png";
    private static final String CONVERT_TABLE_IMG_PATH = "images/conversion-table.png";

    public ConvertScreen() {
        JFrame convertFrame = new JFrame();

        // Window
        convertFrame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        convertFrame.setTitle("CONVERT");
        convertFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        convertFrame.setResizable(false);
        convertFrame.setLocationRelativeTo(null);
        convertFrame.setLayout(null);
        convertFrame.getContentPane().setBackground(Color.WHITE);


        // Title label
        JLabel Title = new JLabel();
        Title.setText("<html><span style='color:red;'>PEPPER</span><span style='color:green;'>.FY</span></html>");									// Change text color to red
        Title.setFont(new Font("Arial", Font.BOLD, 35));
        Title.setBounds(400, 10, 300, 60);
        convertFrame.add(Title);

        // BPM label
        JLabel BPMLabel = new JLabel();
        BPMLabel.setText("BPM:");
        BPMLabel.setFont(new Font("Arial", Font.BOLD, 15));
        BPMLabel.setBounds(680, 575, 100, 30);
        convertFrame.add(BPMLabel);

        // inputBPM
        SpinnerNumberModel spinModelBPM = new SpinnerNumberModel(30, 30, 180, 1);
        JSpinner inputBPM = new JSpinner(spinModelBPM);
        inputBPM.setBounds(730, 575, 50, 30);
        convertFrame.add(inputBPM);

        // Current octave label
        JLabel currentOctaveLabel = new JLabel();
        currentOctaveLabel.setText("Current Octave:");
        currentOctaveLabel.setFont(new Font("Arial", Font.BOLD, 15));
        currentOctaveLabel.setBounds(810, 575, 140, 30);
        convertFrame.add(currentOctaveLabel);

        // inputOctave
        SpinnerNumberModel spinModelOctave = new SpinnerNumberModel(1, 1, 8, 1);
        JSpinner inputOctave = new JSpinner(spinModelOctave);
        inputOctave.setBounds(935, 575, 50, 30);
        convertFrame.add(inputOctave);

        // Volume label
        JLabel volumeLabel = new JLabel();
        volumeLabel.setText("Volume:");
        volumeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        volumeLabel.setBounds(680, 635, 140, 30);
        convertFrame.add(volumeLabel);

        // Volume slider
        JSlider sliderVolume = new JSlider(0, 100, 50);
        sliderVolume.setBounds(750, 630, 200, 50);
        sliderVolume.setMajorTickSpacing(20);
        sliderVolume.setMinorTickSpacing(5);
        sliderVolume.setPaintTicks(true);
        sliderVolume.setPaintLabels(true);
        convertFrame.add(sliderVolume);

        // Pepper.Fy icon
        ImageIcon pepperfyIcon = new ImageIcon(SMALL_PEPPERFY_ICON_PATH);
        JLabel pepperfyLabel = new JLabel(pepperfyIcon);
        pepperfyLabel.setBounds(580, 10, pepperfyIcon.getIconWidth(), pepperfyIcon.getIconHeight());
        convertFrame.add(pepperfyLabel);

        // Configure a scroll for the textBox
        JTextArea textBox = new JTextArea(2,20);
        JScrollPane scrollPane = new JScrollPane(textBox);
        scrollPane.setBounds(50, 130, 450, 570);
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
        backButton.setBounds(40, 15, back_icon.getIconWidth(), back_icon.getIconHeight());
        backButton.setBackground(Color.WHITE);
        backButton.setBorderPainted(false);
        backButton.setToolTipText("Back");
        backButton.setFocusPainted(false);

        // Action backButton
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                convertFrame.dispose();										// close options window
                CurrentScreen.setCurrentScreen("menu");						// open menu screen
            }
        });


        // Style saveButton
        ImageIcon save_icon = new ImageIcon(SAVE_BUTTON_ICON_PATH);
        JButton saveButton = new JButton(save_icon);
        saveButton.setBounds(70, 93, save_icon.getIconWidth(), save_icon.getIconHeight());
        saveButton.setBackground(Color.WHITE);
        saveButton.setBorderPainted(false);
        saveButton.setToolTipText("Save");
        convertFrame.add(saveButton);

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
        pasteButton.setBounds(115, 93, paste_icon.getIconWidth(), paste_icon.getIconHeight());
        pasteButton.setBackground(Color.WHITE);
        pasteButton.setBorderPainted(false);
        pasteButton.setToolTipText("Paste");
        convertFrame.add(pasteButton);

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
        importButton.setBounds(160, 93, import_icon.getIconWidth(), import_icon.getIconHeight());
        importButton.setBackground(Color.WHITE);
        importButton.setBorderPainted(false);
        importButton.setToolTipText("Import");
        convertFrame.add(importButton);

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

        // Style playButton
        ImageIcon play_icon = new ImageIcon(PLAY_BUTTON_ICON_PATH);
        JButton playButton = new JButton(play_icon);
        playButton.setBounds(520, 560, play_icon.getIconWidth(), play_icon.getIconHeight());
        playButton.setBackground(Color.WHITE);
        playButton.setBorderPainted(false);
        playButton.setToolTipText("Play");
        convertFrame.add(playButton);

        // Action playButton
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextSheet currentText= new TextSheet(textBox.getText(),0);
            }
        });

        // Conversion table
        ImageIcon tableIcon = new ImageIcon(CONVERT_TABLE_IMG_PATH);
        JLabel tableLabel = new JLabel(tableIcon);
        tableLabel.setBounds(520, 130, tableIcon.getIconWidth(), tableIcon.getIconHeight());
        convertFrame.add(tableLabel);

        convertFrame.setVisible(true);
    }
}
