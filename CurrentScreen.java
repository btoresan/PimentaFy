public class CurrentScreen {

    private static final String MENU = "menu";
    private static final String CONVERT = "convert";
    private static final String TUTORIAL = "tutorial";

    // decides which screen will be opened
    public static void setCurrentScreen(String screen) {
        if (screen.equals(MENU)) {
            new MenuScreen();
        }

        else if (screen.equals(CONVERT)) {
            new ConvertScreen();
        }

        else if (screen.equals(TUTORIAL)) {
            new TutorialScreen();
        }

    }
}
