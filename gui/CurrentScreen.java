package gui;
public class CurrentScreen {
    private static final String MENU = "menu";
    private static final String CONVERT = "convert";
    private static final String TUTORIAL = "tutorial";

    // decides which screen will be opened
    public static void setCurrentScreen(String screen) {
        switch (screen) {
            case MENU -> new MenuScreen();
            case CONVERT -> new ConvertScreen();
            case TUTORIAL -> new TutorialScreen();
        }
    }
}
