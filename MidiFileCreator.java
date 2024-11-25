import javax.sound.midi.*;
import java.io.File;
import java.util.ArrayList;

public class MidiFileCreator {

    public static void saveAsMidiFile(ArrayList<Sound> sounds, String filePath) throws Exception {
        // Create a new MIDI sequence
        Sequence sequence = new Sequence(Sequence.PPQ, 480); // PPQ = Pulses Per Quarter Note
        Track track = sequence.createTrack();

        long currentTick = 0; // Keeps track of when each note should start

        // Process each sound
        for (Sound sound : sounds) {
            // Set instrument for the sound
            track.add(createProgramChangeEvent(sound.instrument, currentTick));

            // Add the note on and note off events
            track.add(createNoteEvent(ShortMessage.NOTE_ON, sound.pitch, sound.volume, currentTick));
            track.add(createNoteEvent(ShortMessage.NOTE_OFF, sound.pitch, sound.volume, currentTick + sound.duration));

            // Update the current tick for the next note
            currentTick += sound.duration;
        }

        // Write the MIDI sequence to a file
        File midiFile = new File(filePath);
        MidiSystem.write(sequence, 1, midiFile);
        System.out.println("MIDI file created: " + filePath);
    }

    private static MidiEvent createNoteEvent(int command, int pitch, int velocity, long tick) throws Exception {
        ShortMessage message = new ShortMessage();
        message.setMessage(command, 0, pitch, velocity);
        return new MidiEvent(message, tick);
    }

    private static MidiEvent createProgramChangeEvent(int instrument, long tick) throws Exception {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.PROGRAM_CHANGE, 0, instrument, 0);
        return new MidiEvent(message, tick);
    }

    public static void main(String[] args) {
        try {
            // Create an ArrayList of sounds
            ArrayList<Sound> sounds = new ArrayList<>();
            sounds.add(new Sound(60, 0, 100, 480)); // Middle C, Piano, Velocity 100, Duration 1 quarter note
            sounds.add(new Sound(62, 10, 100, 480)); // D note, Music Box
            sounds.add(new Sound(64, 40, 100, 480)); // E note, Synth Lead

            saveAsMidiFile(sounds, "output_sequential.mid");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}