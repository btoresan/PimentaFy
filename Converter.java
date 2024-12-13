import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Converter {
    public static int defaultOctave;
    public static int defaultVolume;
    public static int defaultInstrument;
    public static int defaultBPM;

    private static int currentOffsetIndex;
    private static int currentOctave;
    private static int currentVolume;
    private static int currentInstrument;
    private static int currentBPM;

    private static final int A_OFFSET = 0;
    private static final int B_OFFSET = 1;
    private static final int C_OFFSET = 2;
    private static final int D_OFFSET = 3;
    private static final int E_OFFSET = 4;
    private static final int F_OFFSET = 5;
    private static final int G_OFFSET = 6;

    private static final int noteOffset[] = {9, 11, 0, 2, 4, 5, 7};

    public static void setDefaultConfig(int octave, int volume, int instrument, int bpm) {
        currentOctave = defaultOctave = octave;
        currentVolume = defaultVolume = volume;
        currentInstrument = defaultInstrument = instrument;
        currentBPM = defaultBPM = bpm;
    }

    private static void saveLastSound(int offsetIndex, int octave, int instrument, int volume, int BPM) {
        currentOffsetIndex = offsetIndex;
        currentOctave = octave;
        currentInstrument = instrument;
        currentVolume = volume;
        currentBPM = BPM;
    }

    private static Sound getNextSound(int offsetIndex, int octave, int instrument, int volume, int bpm) {
        int offset = noteOffset[offsetIndex];

        Sound nextSound = new Sound(offset, octave, instrument, volume, bpm);
        saveLastSound(offsetIndex, octave, instrument, volume, bpm);

        return nextSound;
    }

    private static int getRandomBPM(Random randomValue) {
        return randomValue.nextInt(30, 180);
    }

    private static Boolean isNoteAction(String action) {
        if (Objects.equals(action, "la") || Objects.equals(action, "si") || Objects.equals(action, "do") || Objects.equals(action, "re") || Objects.equals(action, "mi") || Objects.equals(action, "fa") || Objects.equals(action, "sol"))
            return true;

        return false;
    }

    public static ArrayList<Sound> convertToSounds(List<String> actionsList) {
        ArrayList<Sound> sounds = new ArrayList<>();
        Random randomValue = new Random();

        for (int i=0; i<actionsList.size(); i++) {
            switch (actionsList.get(i)) {
                case "la":
                    sounds.add(getNextSound(A_OFFSET, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "si":
                    sounds.add(getNextSound(B_OFFSET, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "do":
                    sounds.add(getNextSound(C_OFFSET, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "re":
                    sounds.add(getNextSound(D_OFFSET, currentOctave, currentInstrument, currentVolume, currentBPM));;
                    break;
                case "mi":
                    sounds.add(getNextSound(E_OFFSET, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "fa":
                    sounds.add(getNextSound(F_OFFSET, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "sol":
                    sounds.add(getNextSound(G_OFFSET, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "silence":
                    sounds.add(getNextSound(currentOffsetIndex, currentOctave, currentInstrument, 0, currentBPM));
                    break;
                case "double volume":
                    sounds.add(getNextSound(currentOffsetIndex, currentOctave, currentInstrument, currentVolume*2, currentBPM));
                    break;
                case "default volume":
                    sounds.add(getNextSound(currentOffsetIndex, currentOctave, currentInstrument, defaultVolume, currentBPM));
                    break;
                case "repeat or phone":
                    if (isNoteAction(actionsList.get(i-1)))
                        sounds.add(getNextSound(currentOffsetIndex, currentOctave, currentInstrument, currentVolume, currentBPM));
                    else
                        sounds.add(getNextSound(currentOffsetIndex, currentOctave, 125, currentVolume, currentBPM));
                    break;
                case "plus one octave":
                    sounds.add(getNextSound(currentOffsetIndex, currentOctave+1, currentInstrument, currentVolume, currentBPM));
                    break;
                case "minus one octave":
                    sounds.add(getNextSound(currentOffsetIndex, currentOctave-1, currentInstrument, currentVolume, currentBPM));
                    break;
                case "random note":
                    int randomFrequency = randomValue.nextInt(7);
                    sounds.add(getNextSound(randomFrequency, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "plus one instrument":
                    sounds.add(getNextSound(currentOffsetIndex, currentOctave, currentInstrument+1, currentVolume, currentBPM));
                    break;
                case "plus 80 bpm":
                    sounds.add(getNextSound(currentOffsetIndex, currentOctave, currentInstrument, currentVolume, currentBPM+80));
                    break;
                case "random bpm":
                    int randomBPM = getRandomBPM(randomValue);
                    sounds.add(getNextSound(currentOffsetIndex, currentOctave, currentInstrument, currentVolume, randomBPM));
                    break;
                case "nop":
                    sounds.add(getNextSound(currentOffsetIndex, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "":
                    break;
                default:
            }
        }

        return sounds;
    }
}
