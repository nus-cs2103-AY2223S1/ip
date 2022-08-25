package duke.util;

/**
 * Class that deals with interactions with the user.
 */
public class Ui {
    private static final String LOGO = "        Duke\n";

    private static final String GREET = "~~~~ Hello I am ~~~~\n" + LOGO + "What do you need help with?";

    private static final String BYE = "See you soon! Don't worry, I saved the duke.data!";

    /**
     * Formats an input message.
     *
     * @param input the input
     */
    public static void formatMessage(String input) {
        System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n"
            + input + "\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
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
    public static void bye() {
        formatMessage(BYE);
    }
}
