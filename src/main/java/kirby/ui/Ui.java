package kirby.ui;

/**
 * Ui class handles user interaction such as the different display messages.
 */
public class Ui {
    private static String WELCOME_MESSAGE = "Hai I'm Kirby (੭｡╹▿╹｡)੭  \n" + "What amazing plans do you have today?";
    /**
     * Returns the welcome message.
     * @return String of welcome message.
     */
    public static String showWelcome() {
        return WELCOME_MESSAGE;
    }
}
