package duke.util;

/**
 * Class that deals with interactions with the user.
 */
public class Ui {
    private static final String LOGO = "        Duke\n";

    private static final String GREET = "~~~~ Hello I am ~~~~\n" + LOGO + "What do you need help with?";

    private static final String BYE = "See you soon! Don't worry, I saved the data!";

    /**
     * Formats an input message.
     *
     * @param input the input
     */
    public static String formatMessage(String input) {
        return("-------------------------------\n"
            + input + "\n-------------------------------");
    }

    /**
     * Greetings.
     */
    public static void greet() {
        formatMessage(GREET);
    }

    /**
     * Bye.
     */
    public static String bye() {
        return formatMessage(BYE);
    }
}
