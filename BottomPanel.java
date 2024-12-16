import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BottomPanel extends JPanel {
    private static final String PLAY_BUTTON_ICON_PATH = "images/play_icon.png";
    private static final String[] INSTRUMENTS = {"Electric Piano 1","Acoustic Guitar","Acoustic Bass", "Violin","Viola","Trumpet","Tuba", "Reed Organ", "Alto Sax", "Clarinet", "Electric Guitar"};

    public BottomPanel(CentralPanel centralPanel,JFrame convertFrame) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        // BPM label
        JLabel BPMLabel = new JLabel();
        BPMLabel.setText("BPM:");
        BPMLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(BPMLabel, gbc);

        // inputBPM
        SpinnerNumberModel spinModelBPM = new SpinnerNumberModel(60, 30, 600, 1);
        JSpinner inputBPM = new JSpinner(spinModelBPM);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 40);
        add(inputBPM, gbc);

        // Octave label
        JLabel OctaveLabel = new JLabel();
        OctaveLabel.setText("Octave:");
        OctaveLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(OctaveLabel, gbc);

        // inputOctave
        SpinnerNumberModel spinModelOctave = new SpinnerNumberModel(4, 1, 8, 1);
        JSpinner inputOctave = new JSpinner(spinModelOctave);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 40);
        add(inputOctave, gbc);

        // Volume label
        JLabel volumeLabel = new JLabel();
        volumeLabel.setText("Volume:");
        volumeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 40);
        add(volumeLabel, gbc);

        // Volume slider
        JSlider sliderVolume = new JSlider(0, 100, 50);
        sliderVolume.setMajorTickSpacing(20);
        sliderVolume.setMinorTickSpacing(5);
        sliderVolume.setPaintTicks(true);
        sliderVolume.setPaintLabels(true);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 40);
        add(sliderVolume, gbc);

        // Instrument label
        JLabel instrumentLabel = new JLabel();
        instrumentLabel.setText("Instrument:");
        instrumentLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 40);
        add(instrumentLabel, gbc);

        // Instrument selector
        JComboBox<String> instrumentOptions = new JComboBox<>();
        for (String instrument : INSTRUMENTS) {
            instrumentOptions.addItem(instrument);
        }
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 40);
        add(instrumentOptions, gbc);

        // Style saveMIDIButton
        JButton saveMIDIButton = new JButton();
        saveMIDIButton.setText("Save MIDI");
        saveMIDIButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 15));
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(saveMIDIButton, gbc);

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
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.insets = new Insets(5, 5, 5, 40);
        add(playButton, gbc);

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

    }

    private int extractInstrument (String instrumentText){
        return switch (instrumentText) {
            case "Electric Piano 1" -> 4;
            case "Acoustic Guitar" -> 24;
            case "Acoustic Bass" -> 32;
            case "Violin" -> 40;
            case "Viola" -> 41;
            case "Trumpet" -> 56;
            case "Tuba" -> 58;
            case "Reed Organ" -> 20;
            case "Alto Sax" -> 65;
            case "Clarinet" -> 71;
            case "Electric Guitar" -> 28;
            case null -> 0;
            default -> 1;
        };
    }
}
