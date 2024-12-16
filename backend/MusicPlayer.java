package backend;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.ArrayList;

public class MusicPlayer {
    private Synthesizer synthesizer;
    private ArrayList<Sound> music;

    //Constructor Class
    public MusicPlayer(ArrayList<Sound> music){
        setMusic(music);
    }

    //Iterate through the sounds in the music and plays them
    public void play() {
        startSynthesizer();

        for (Sound sound : music) {
            int currentPitch = sound.getPitch();
            int currentInstrument = sound.getInstrument();
            int currentVolume = sound.getVolume();
            int currentDuration = sound.getDuration();

            playNote(currentPitch, currentVolume, currentInstrument, currentDuration);
        }

        closeSynthesizer();
    }

    //Allows user to change the music without creating a new instance
    public void setMusic(ArrayList<Sound> userMusic) {
        music = userMusic;
    }

    private void startSynthesizer() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open(); // Open the synthesizer here to use later
        } catch (MidiUnavailableException e) {
            System.out.println("An error occurred opening the synthesizer");
        }
    }

    private void closeSynthesizer() {
        if (synthesizer != null && synthesizer.isOpen()) {
            synthesizer.close();
        }
    }

    private void playNote(int pitch, int volume, int instrument, int duration) {
        try {
            // Get a MIDI channel to play notes
            MidiChannel[] channels = synthesizer.getChannels();
            MidiChannel channel = channels[0];
            channel.programChange(instrument);

            // Play the note
            channel.noteOn(pitch, volume);
            Thread.sleep(duration);
            channel.noteOff(pitch);

        } catch (Exception e) {
            System.out.println("An error occurred trying to play a note");
        }
    }
}