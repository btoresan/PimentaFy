import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ConvertScreen {

    private static final int WINDOW_WIDTH ;
    private static final int WINDOW_HEIGHT;
    private static final String SMALL_PEPPERFY_ICON_PATH = "images/small_pepperfy_icon.png";
    private static final String BACK_BUTTON_ICON_PATH = "images/back_icon.png";
    private static final String SAVE_BUTTON_ICON_PATH = "images/save_icon.png";
    private static final String PASTE_BUTTON_ICON_PATH = "images/paste_icon.png";
    private static final String IMPORT_BUTTON_ICON_PATH = "images/import_icon.png";
    private static final String PLAY_BUTTON_ICON_PATH = "images/play_icon.png";
    private static final String CONVERT_TABLE_IMG_PATH = "images/conversion-table.png";
    private static final String[] INSTRUMENTS = {"Electric Piano 1","Acoustic Guitar","Acoustic Bass", "Violin","Viola","Trumpet","Tuba", "Reed Organ", "Alto Sax", "Clarinet", "Electric Guitar"};

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WINDOW_WIDTH = screenSize.width;
        WINDOW_HEIGHT = screenSize.height;
    }

    private int extractInstrument (String instrumentText){
        switch (instrumentText){
            case "Electric Piano 1":
               return  4;

            case "Acoustic Guitar":
                return  24;

            case "Acoustic Bass":
                return 32;

            case "Violin":
                return 40;

            case "Viola":
                return  41;

            case "Trumpet":
                return 56;

            case "Tuba":
                return 58;

            case "Reed Organ":
                return 20;

            case "Alto Sax":
                return 65;

            case "Clarinet":
                return 71;

            case "Electric Guitar":
                return 28;

            default:
                return 1;
        }
    }

    public ConvertScreen() {

        // Window
        JFrame convertFrame = new JFrame();
        convertFrame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        convertFrame.setTitle("CONVERT");
        convertFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        convertFrame.setResizable(true);
        convertFrame.setLocationRelativeTo(null);
        convertFrame.setLayout(null);
        convertFrame.getContentPane().setBackground(Color.WHITE);

        // Layout manager window
        convertFrame.setLayout(new BorderLayout());

        // Layout manager panels
        JPanel centralTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centralTopPanel.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JPanel centralPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcCentralPanel = new GridBagConstraints();
        centralPanel.setBackground(Color.WHITE);

        ImagePanel imagePanel = new ImagePanel(CONVERT_TABLE_IMG_PATH);

        JPanel fileButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fileButtonsPanel.setBackground(Color.WHITE);

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcBottomPanel = new GridBagConstraints();
        bottomPanel.setBackground(Color.WHITE);

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
                convertFrame.dispose();										// close options window
                CurrentScreen.setCurrentScreen("menu");						// open menu screen
            }
        });

        // -------------------- CENTRAL PANEL --------------------

        // Configure a scroll for the textBox
        JTextArea textBox = new JTextArea(30, 5);
        JScrollPane scrollPane = new JScrollPane(textBox);
        gbcCentralPanel.gridx = 0;
        gbcCentralPanel.gridy = 1;
        gbcCentralPanel.gridwidth = 1;
        gbcCentralPanel.weightx = 1.0;
        gbcCentralPanel.weighty = 1.0;
        gbcCentralPanel.fill = GridBagConstraints.BOTH;
        gbcCentralPanel.insets = new Insets(0, 10, 10, 10);
        centralPanel.add(scrollPane, gbcCentralPanel);

        // Text box
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textBox.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);

        // Style saveButton
        ImageIcon save_icon = new ImageIcon(SAVE_BUTTON_ICON_PATH);
        JButton saveButton = new JButton(save_icon);
        saveButton.setBackground(Color.WHITE);
        saveButton.setBorderPainted(false);
        saveButton.setToolTipText("Save");
        fileButtonsPanel.add(saveButton);

        // Action saveButton
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create a JFileChooser to save the file
                JFileChooser saveChooser = new JFileChooser();
                saveChooser.setDialogTitle("Save as");

                // Configure the filter to save only text files
                saveChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));

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
        pasteButton.setBackground(Color.WHITE);
        pasteButton.setBorderPainted(false);
        pasteButton.setToolTipText("Paste");
        fileButtonsPanel.add(pasteButton);

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
        importButton.setBackground(Color.WHITE);
        importButton.setBorderPainted(false);
        importButton.setToolTipText("Import");
        fileButtonsPanel.add(importButton);

        // Action importButton
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create a JFileChooser to open the file
                JFileChooser importChooser = new JFileChooser();
                importChooser.setDialogTitle("Open File");

                // Configure the filter to show only text files
                importChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));

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

        // Adding fileButtonsPanel to centralPanel
        gbcCentralPanel.gridx = 0;
        gbcCentralPanel.gridy = 0;
        gbcCentralPanel.gridwidth = 1;
        gbcCentralPanel.weightx = 1;
        gbcCentralPanel.weighty = 0;
        gbcCentralPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcCentralPanel.insets = new Insets(0, 0, 0, 0);
        centralPanel.add(fileButtonsPanel,gbcCentralPanel);

        // Conversion table
        gbcCentralPanel.gridx = 1;
        gbcCentralPanel.gridy = 1;
        gbcCentralPanel.gridwidth = 1;
        gbcCentralPanel.weightx = 1.0;
        gbcCentralPanel.weighty = 1.0;
        gbcCentralPanel.fill = GridBagConstraints.BOTH;
        gbcCentralPanel.insets = new Insets(0, 10, 10, 10);
        centralPanel.add(imagePanel,gbcCentralPanel);


        // -------------------- BOTTOM PANEL --------------------

        // BPM label
        JLabel BPMLabel = new JLabel();
        BPMLabel.setText("BPM:");
        BPMLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbcBottomPanel.gridx = 1;
        gbcBottomPanel.gridy = 1;
        gbcBottomPanel.gridwidth = 1;
        gbcBottomPanel.gridheight = 1;
        gbcBottomPanel.insets = new Insets(5, 5, 5, 5);
        bottomPanel.add(BPMLabel, gbcBottomPanel);

        // inputBPM
        SpinnerNumberModel spinModelBPM = new SpinnerNumberModel(60, 30, 600, 1);
        JSpinner inputBPM = new JSpinner(spinModelBPM);
        gbcBottomPanel.gridx = 2;
        gbcBottomPanel.gridy = 1;
        gbcBottomPanel.insets = new Insets(5, 5, 5, 40);
        bottomPanel.add(inputBPM, gbcBottomPanel);

        // Octave label
        JLabel OctaveLabel = new JLabel();
        OctaveLabel.setText("Octave:");
        OctaveLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbcBottomPanel.gridx = 1;
        gbcBottomPanel.gridy = 2;
        gbcBottomPanel.insets = new Insets(5, 5, 5, 5);
        bottomPanel.add(OctaveLabel, gbcBottomPanel);


        // inputOctave
        SpinnerNumberModel spinModelOctave = new SpinnerNumberModel(4, 1, 8, 1);
        JSpinner inputOctave = new JSpinner(spinModelOctave);
        gbcBottomPanel.gridx = 2;
        gbcBottomPanel.gridy = 2;
        gbcBottomPanel.insets = new Insets(5, 5, 5, 40);
        bottomPanel.add(inputOctave, gbcBottomPanel);

        // Volume label
        JLabel volumeLabel = new JLabel();
        volumeLabel.setText("Volume:");
        volumeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbcBottomPanel.gridx = 3;
        gbcBottomPanel.gridy = 1;
        gbcBottomPanel.insets = new Insets(5, 5, 5, 40);
        bottomPanel.add(volumeLabel, gbcBottomPanel);

        // Volume slider
        JSlider sliderVolume = new JSlider(0, 100, 50);
        sliderVolume.setMajorTickSpacing(20);
        sliderVolume.setMinorTickSpacing(5);
        sliderVolume.setPaintTicks(true);
        sliderVolume.setPaintLabels(true);
        gbcBottomPanel.gridx = 3;
        gbcBottomPanel.gridy = 2;
        gbcBottomPanel.insets = new Insets(5, 5, 5, 40);
        bottomPanel.add(sliderVolume, gbcBottomPanel);

        // Instrument label
        JLabel instrumentLabel = new JLabel();
        instrumentLabel.setText("Instrument:");
        instrumentLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbcBottomPanel.gridx = 4;
        gbcBottomPanel.gridy = 1;
        gbcBottomPanel.insets = new Insets(5, 5, 5, 40);
        bottomPanel.add(instrumentLabel, gbcBottomPanel);

        // Instrument selector
        JComboBox<String> instrumentOptions = new JComboBox<>();
        for (String instrument : INSTRUMENTS) {
            instrumentOptions.addItem(instrument);
        }
        gbcBottomPanel.gridx = 4;
        gbcBottomPanel.gridy = 2;
        gbcBottomPanel.insets = new Insets(5, 5, 5, 40);
        bottomPanel.add(instrumentOptions, gbcBottomPanel);

        // Style saveMIDIButton
        JButton saveMIDIButton = new JButton();
        saveMIDIButton.setText("Save MIDI");
        saveMIDIButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 15));
        gbcBottomPanel.gridx = 5;
        gbcBottomPanel.gridy = 2;
        gbcBottomPanel.insets = new Insets(5, 5, 5, 5);
        bottomPanel.add(saveMIDIButton, gbcBottomPanel);

        // Action saveMIDIButton
        saveMIDIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TextSheet currentText= new TextSheet(textBox.getText(),0);
                int defaultBPM = (int) inputBPM.getValue();
                int defaultOctave = (int) inputOctave.getValue();
                int defaultVolume = sliderVolume.getValue();
                int defaultInstrument=0;

                String selectedInstrument = (String) instrumentOptions.getSelectedItem();

                defaultInstrument = extractInstrument(selectedInstrument);
                Converter.setDefaultConfig(defaultOctave,defaultVolume,defaultInstrument,defaultBPM);
                List<String> actions = MappingTable.convertToActions(currentText.getText());
                ArrayList<Sound> sounds = Converter.convertToSounds(actions);

                JFileChooser MIDIFileChooser = new JFileChooser();
                MIDIFileChooser.setDialogTitle("Save MIDI file");

                // Configuring default .mid extension
                MIDIFileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("MIDI file (*.mid)", "mid"));

                int userSelection = MIDIFileChooser.showSaveDialog(convertFrame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = MIDIFileChooser.getSelectedFile();

                    // Adds .mid extension if user does not specify it
                    String filePath = fileToSave.getAbsolutePath();
                    if (!filePath.toLowerCase().endsWith(".mid")) {
                        filePath += ".mid";
                    }

                    // Salvar arquivo
                    try {
                        MidiFileCreator.saveAsMidiFile(sounds, filePath);
                        JOptionPane.showMessageDialog(convertFrame, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(convertFrame, "Error saving file:" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        // Style playButton
        ImageIcon play_icon = new ImageIcon(PLAY_BUTTON_ICON_PATH);
        JButton playButton = new JButton(play_icon);
        playButton.setBackground(Color.WHITE);
        playButton.setBorderPainted(false);
        playButton.setToolTipText("Play");

        gbcBottomPanel.gridx = 0;
        gbcBottomPanel.gridy = 1;
        gbcBottomPanel.gridheight = 3;
        gbcBottomPanel.insets = new Insets(5, 5, 5, 40);
        bottomPanel.add(playButton, gbcBottomPanel);

        // Action playButton
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextSheet currentText= new TextSheet(textBox.getText(),0);
                int defaultBPM = (int) inputBPM.getValue();
                int defaultOctave = (int) inputOctave.getValue();
                int defaultVolume = sliderVolume.getValue();
                int defaultInstrument=0;

                String selectedInstrument = (String) instrumentOptions.getSelectedItem();

                defaultInstrument = extractInstrument(selectedInstrument);
                Converter.setDefaultConfig(defaultOctave,defaultVolume,defaultInstrument,defaultBPM);
                List<String> actions = MappingTable.convertToActions(currentText.getText());
                ArrayList<Sound> sounds = Converter.convertToSounds(actions);
                MusicPlayer musicPlayer = new MusicPlayer(sounds);
                musicPlayer.play();
            }
        });


        // Adding the created panels to the window
        convertFrame.add(topPanel,BorderLayout.NORTH);
        convertFrame.add(centralPanel,BorderLayout.CENTER);
        convertFrame.add(bottomPanel,BorderLayout.SOUTH);

        convertFrame.setVisible(true);
    }
}

