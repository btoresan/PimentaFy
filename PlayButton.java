import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayButton extends JButton {
    private static final String PLAY_BUTTON_ICON_PATH = "images/play_icon.png";

    public PlayButton(
            JTextArea textBox,
            JSpinner inputBPM,
            JSpinner inputOctave,
            JSlider sliderVolume,
            JComboBox<String> instrumentOptions) {

        // Style PlayButton
        setIcon(new ImageIcon(PLAY_BUTTON_ICON_PATH));
        setBackground(Color.WHITE);
        setBorderPainted(false);
        setToolTipText("Play");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TextSheet currentText = new TextSheet(textBox.getText(), 0);
                int defaultBPM = (int) inputBPM.getValue();
                int defaultOctave = (int) inputOctave.getValue();
                int defaultVolume = sliderVolume.getValue();

                String selectedInstrument = (String) instrumentOptions.getSelectedItem();
                int defaultInstrument = BottomPanel.extractInstrument(selectedInstrument);

                Converter.setDefaultConfig(defaultOctave, defaultVolume, defaultInstrument, defaultBPM);

                List<String> actions = MappingTable.convertToActions(currentText.getText());
                ArrayList<Sound> sounds = Converter.convertToSounds(actions);

                MusicPlayer musicPlayer = new MusicPlayer(sounds);
                musicPlayer.play();
            }
        });
    }
}
