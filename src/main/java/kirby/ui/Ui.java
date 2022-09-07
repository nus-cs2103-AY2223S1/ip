package kirby.ui;

/**
 * Ui class handles user interaction such as the different display messages.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = new StringBuilder().append("Hai I'm Kirby (੭｡╹▿╹｡)੭  \n")
            .append("What amazing plans do you have today? \n")
            .append("Type help to get started!").toString();
    /**
     * Returns the welcome message.
     * @return String of welcome message.
     */
    public static String showWelcome() {
        return WELCOME_MESSAGE;
    }
}
