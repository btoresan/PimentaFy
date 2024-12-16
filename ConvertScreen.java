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
    private static final String PLAY_BUTTON_ICON_PATH = "images/play_icon.png";
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

            case null:
                return 0;
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

        // Panels for the convertFrame
        TopPanel topPanel = new TopPanel(convertFrame);
        CentralPanel centralPanel = new CentralPanel();


        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcBottomPanel = new GridBagConstraints();
        bottomPanel.setBackground(Color.WHITE);


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

                TextSheet currentText= new TextSheet((centralPanel.getTextBox()).getText(),0);
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
                TextSheet currentText= new TextSheet((centralPanel.getTextBox()).getText(),0);
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

