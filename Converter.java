import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Converter {
    public static int defaultOctave;
    public static int defaultVolume;
    public static int defaultInstrument;
    public static int defaultBPM;

    private static int currentFrequencyIndex;
    private static int currentOctave;
    private static int currentVolume;
    private static int currentInstrument;
    private static int currentBPM;

    private static final int A_FREQUENCY = 0;
    private static final int B_FREQUENCY = 1;
    private static final int C_FREQUENCY = 2;
    private static final int D_FREQUENCY = 3;
    private static final int E_FREQUENCY = 4;
    private static final int F_FREQUENCY = 5;
    private static final int G_FREQUENCY = 6;

    private static final float frequencies[] = {55, 61.735F, 32.703F, 36.708F, 41.203F, 43.654F, 48.999F};

    public static void setDefaultConfig(int octave, int volume, int instrument, int bpm) {
        defaultOctave = octave;
        defaultVolume = volume;
        defaultInstrument = instrument;
        defaultBPM = bpm;
    }

    private static void saveLastSound(int frequencyIndex, int octave, int instrument, int volume, int BPM) {
        currentFrequencyIndex = frequencyIndex;
        currentOctave = octave;
        currentInstrument = instrument;
        currentVolume = volume;
        currentBPM = BPM;
    }

    private static Sound getNextSound(int frequencyIndex, int octave, int instrument, int volume, int bpm) {
        float frequency = frequencies[frequencyIndex];

        Sound nextSound = new Sound(frequency, octave, instrument, volume, bpm);
        saveLastSound(frequencyIndex, octave, instrument, volume, bpm);

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
                    sounds.add(getNextSound(A_FREQUENCY, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "si":
                    sounds.add(getNextSound(B_FREQUENCY, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "do":
                    sounds.add(getNextSound(C_FREQUENCY, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "re":
                    sounds.add(getNextSound(D_FREQUENCY, currentOctave, currentInstrument, currentVolume, currentBPM));;
                    break;
                case "mi":
                    sounds.add(getNextSound(E_FREQUENCY, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "fa":
                    sounds.add(getNextSound(F_FREQUENCY, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "sol":
                    sounds.add(getNextSound(G_FREQUENCY, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "silence":
                    sounds.add(getNextSound(currentFrequencyIndex, currentOctave, currentInstrument, 0, currentBPM));
                    break;
                case "double volume":
                    sounds.add(getNextSound(currentFrequencyIndex, currentOctave, currentInstrument, currentVolume*2, currentBPM));
                    break;
                case "default volume":
                    sounds.add(getNextSound(currentFrequencyIndex, currentOctave, currentInstrument, defaultVolume, currentBPM));
                    break;
                case "repeat or phone":
                    if (isNoteAction(actionsList.get(i-1)))
                        sounds.add(getNextSound(currentFrequencyIndex, currentOctave, currentInstrument, currentVolume, currentBPM));
                    else
                        sounds.add(getNextSound(currentFrequencyIndex, currentOctave, 125, currentVolume, currentBPM));
                    break;
                case "plus one octave":
                    sounds.add(getNextSound(currentFrequencyIndex, currentOctave+1, currentInstrument, currentVolume, currentBPM));
                    break;
                case "minus one octave":
                    sounds.add(getNextSound(currentFrequencyIndex, currentOctave-1, currentInstrument, currentVolume, currentBPM));
                    break;
                case "random note":
                    int randomFrequency = randomValue.nextInt(7);
                    sounds.add(getNextSound(randomFrequency, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "plus one instrument":
                    sounds.add(getNextSound(currentFrequencyIndex, currentOctave, currentInstrument+1, currentVolume, currentBPM));
                    break;
                case "plus 80 bpm":
                    sounds.add(getNextSound(currentFrequencyIndex, currentOctave, currentInstrument, currentVolume, currentBPM+80));
                    break;
                case "random bpm":
                    int randomBPM = getRandomBPM(randomValue);
                    sounds.add(getNextSound(currentFrequencyIndex, currentOctave, currentInstrument, currentVolume, randomBPM));
                    break;
                case "nop":
                    sounds.add(getNextSound(currentFrequencyIndex, currentOctave, currentInstrument, currentVolume, currentBPM));
                    break;
                case "":
                    break;
                default:
            }
        }

        return sounds;
    }
}
