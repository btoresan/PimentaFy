import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class MappingTable {
    private static final HashMap<String, String> singleActions = new HashMap<>();
    private static final HashMap<String, String> multiActions = new HashMap<>();

    static {
        // Define single-character mappings
        singleActions.put("A", "la");
        singleActions.put("B", "si");
        singleActions.put("C", "do");
        singleActions.put("D", "re");
        singleActions.put("E", "mi");
        singleActions.put("F", "fa");
        singleActions.put("G", "sol");

        for (char letter = 'a'; letter <= 'g'; letter++) {
            singleActions.put(String.valueOf(letter), "previous or silence");
        }
        singleActions.put("!", "Instrument 14");
        singleActions.put("I", "Instrument 71");
        singleActions.put("i", "Instrument 71");
        singleActions.put("O", "Instrument 71");
        singleActions.put("o", "Instrument 71");
        singleActions.put("U", "Instrument 71");
        singleActions.put("u", "Instrument 71");

        String consonants = "BHJKLMNPQRSTVWXYZ" + "bhjklmnpqrstvwxyz";
        for (char letter : consonants.toCharArray()) {
            singleActions.put(String.valueOf(letter), "previous or silence");
            // Perform actions with each letter here, like adding to a map
        }

        String evenDigits = "02468";
        for (char letter : evenDigits.toCharArray()) {
            singleActions.put(String.valueOf(letter), "instrument + "+letter);
            // Perform actions with each letter here, like adding to a map
        }

        singleActions.put("?", "+ one octave");
        singleActions.put("\n", "Instrument 15");
        singleActions.put(";", "Instrument 126");
        singleActions.put(",", "Instrument 105");

        // Define multi-character overrides
        multiActions.put("BPM+", "faster notes");
    }

    public static List<String> convertToActions(String input) {
        List<String> output = new ArrayList<>();
        int i = 0;

        while (i < input.length()) {
            boolean matched = false;

            // Check for multi-character matches
            for (String action : multiActions.keySet()) {
                if (input.startsWith(action, i)) {
                    output.add(multiActions.get(action));
                    i += action.length();
                    matched = true;
                    break;
                }
            }

            // If no multi-character match, check single-character actions
            if (!matched) {
                String charStr = Character.toString(input.charAt(i));
                output.add(singleActions.getOrDefault(charStr, "previous or silence"));
                i++;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        String input = "BPM+AB-";
        List<String> notes = convertToActions(input);
        System.out.println(notes);
    }
}