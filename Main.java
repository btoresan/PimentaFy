import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //CurrentScreen.setCurrentScreen("menu");
        List<String> actions = MappingTable.convertToActions("a");
        ArrayList<Sound> sounds = Converter.convertToSounds(actions);

        System.out.println(sounds);
    }
}