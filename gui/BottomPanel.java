package gui;
import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {

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
        sliderVolume.setBackground(Color.WHITE);
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

        // Buttons
        SaveMIDIButton saveMIDIButton = new SaveMIDIButton(centralPanel.getTextBox(),inputBPM,inputOctave,sliderVolume,instrumentOptions,convertFrame);
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(saveMIDIButton, gbc);

        PlayButton playButton = new PlayButton(centralPanel.getTextBox(),inputBPM,inputOctave,sliderVolume,instrumentOptions);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.insets = new Insets(5, 5, 5, 40);
        add(playButton, gbc);

    }
    public static int extractInstrument (String instrumentText){
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
