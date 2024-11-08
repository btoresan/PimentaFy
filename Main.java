import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Sound> music = new ArrayList<>();
        Sound sound = new Sound();

        for(int i=0; i < 50; i++) {
            sound.instrument = 7;
            sound.pitch =  2*i+7;
            sound.volume = 1000;
            sound.duration = 1000;

            music.add(sound);

            sound = new Sound();
        }

        MusicPlayer player = new MusicPlayer(music);
        player.play();
    }
}