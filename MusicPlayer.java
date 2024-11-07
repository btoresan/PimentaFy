import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.ArrayList;

public class MusicPlayer {
    private Synthesizer synthesizer;
    private int noteDuration;
    private ArrayList<Sound> music;

    public MusicPlayer(ArrayList<Sound> music, int userBPM){
        setMusic(music);
        setBPM(userBPM);
    }

    public void play() {
        startSynthesizer();

        for (Sound sound : music) {
            int currentPitch = sound.pitch;
            int currentInstrument = sound.instrument;
            int currentVolume = sound.volume;

            playNote(currentPitch, currentVolume, currentInstrument);
        }

        closeSynthesizer();
    }

    public void startSynthesizer() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open(); // Open the synthesizer here to use later
        } catch (MidiUnavailableException e) {
            System.out.println("An error occurred opening the synthesizer");
        }
    }

    public void closeSynthesizer() {
        if (synthesizer != null && synthesizer.isOpen()) {
            synthesizer.close();
        }
    }

    public void setMusic(ArrayList<Sound> userMusic) {
        music = userMusic;
    }

    public void setBPM(int userBPM) {
        noteDuration = 60000/(userBPM);
    }

    public void playNote(int pitch, int volume, int instrument) {
        try {
            // Get a MIDI channel to play notes
            MidiChannel[] channels = synthesizer.getChannels();
            MidiChannel channel = channels[instrument];

            // Play the note
            channel.noteOn(pitch, volume);
            Thread.sleep(noteDuration);
            channel.noteOff(pitch);

        } catch (Exception e) {
            System.out.println("An error occurred trying to play a note");
        }
    }
}

