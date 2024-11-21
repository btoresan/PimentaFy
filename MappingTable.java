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

        singleActions.put("a", "la");
        singleActions.put("b", "si");
        singleActions.put("c", "do");
        singleActions.put("d", "re");
        singleActions.put("e", "mi");
        singleActions.put("f", "fa");
        singleActions.put("g", "sol");

        singleActions.put(" ", "silence");

        singleActions.put("+", "double volume");
        singleActions.put("-", "default volume");

        singleActions.put("I", "repeat or phone");
        singleActions.put("i", "repeat or phone");
        singleActions.put("O", "repeat or phone");
        singleActions.put("o", "repeat or phone");
        singleActions.put("U", "repeat or phone");
        singleActions.put("u", "repeat or phone");

        singleActions.put("?", "random note");
        singleActions.put("\n", "plus one instrument");
        singleActions.put(";", "random instrument");

        // Define multi-character overrides
        multiActions.put("BPM+", "faster notes");
        multiActions.put("R+", "plus one octave");
        multiActions.put("R-", "minus one octave");

        //else is covered in the method
    }

    public static List<String> convertToActions(String input) {
        List<String> output = new ArrayList<>();
        int i = 0;

        //Iterate through the String
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
                output.add(singleActions.getOrDefault(charStr, "nop"));
                i++;
            }
        }
        return output;
    }

    //method to exemplify
    public static void main(String[] args) {
        String input = "BPM+AB-";
        List<String> notes = convertToActions(input);
        System.out.println(notes);
    }
}