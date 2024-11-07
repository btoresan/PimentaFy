import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Sound> music = new ArrayList<>();
        Sound sound = new Sound();

        for(int i=0; i < 100; i++) {
            sound.instrument = 2;
            sound.pitch =  2*i;
            sound.volume = 1000;

            music.add(sound);

            sound = new Sound();
        }

        MusicPlayer player = new MusicPlayer(music, 180);
        player.play();
    }
}