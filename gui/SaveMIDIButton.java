package gui;
import backend.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaveMIDIButton extends JButton {

    public SaveMIDIButton(
            JTextArea textBox,
            JSpinner inputBPM,
            JSpinner inputOctave,
            JSlider sliderVolume,
            JComboBox<String> instrumentOptions,
            JFrame frame) {

        // Style SaveMIDIButton
        setText("Save MIDI");
        setFont(new Font("Arial", Font.ROMAN_BASELINE, 15));

        // Action SaveMIDIButton
        addActionListener(e -> {

            TextSheet currentText = new TextSheet(textBox.getText(), 0);
            int defaultBPM = (int) inputBPM.getValue();
            int defaultOctave = (int) inputOctave.getValue();
            int defaultVolume = sliderVolume.getValue();

            String selectedInstrument = (String) instrumentOptions.getSelectedItem();
            int defaultInstrument = BottomPanel.extractInstrument(selectedInstrument);

            Converter.setDefaultConfig(defaultOctave, defaultVolume, defaultInstrument, defaultBPM);

            // Convert text to actions and sounds
            List<String> actions = MappingTable.convertToActions(currentText.getText());
            ArrayList<Sound> sounds = Converter.convertToSounds(actions);

            // Configure JFileChooser to save MIDI files
            JFileChooser MIDIFileChooser = new JFileChooser();
            MIDIFileChooser.setDialogTitle("Save MIDI file");
            MIDIFileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("MIDI file (*.mid)", "mid"));

            // Show the save dialog
            int userSelection = MIDIFileChooser.showSaveDialog(frame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = MIDIFileChooser.getSelectedFile();

                // Ensure ".mid" extension
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".mid")) {
                    filePath += ".mid";
                }

                // Save MIDI file
                try {
                    MidiFileCreator.saveAsMidiFile(sounds, filePath);
                    JOptionPane.showMessageDialog(frame, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
