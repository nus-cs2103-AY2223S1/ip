package bloop;

/**
 * Interactions with the user.
 */
public class Ui {
    private static final String HI_MESSAGE = "Hey! I'm bloop.Bloop\n" + "\tWhat can I do for you?";

    private static final String BYE_MESSAGE = "Goodbye! Hope to see you soon :)";

    private static final String SEPARATOR = "\t-------------------------------------------------------";

    /**
     * Displays in a formatted manner.
     *
     * @param message Text to be dispalyed.
     */
    public void print(String message) {
        System.out.println(SEPARATOR);
        System.out.println("\t" + message);
        System.out.println(SEPARATOR);
    }

    public String getSeparator() {
        return SEPARATOR;
    }

    /**
     * Displays the message when Bloop starts up.
     */
    public void startMessage() {
        print(HI_MESSAGE);
    }

    /**
     * Displays the message when it is terminated.
     */
    public void endMessage() {
        print(BYE_MESSAGE);
    }

}
