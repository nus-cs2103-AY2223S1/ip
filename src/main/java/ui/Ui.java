package ui;

/**
 * The class for interacting with the user.
 * @author Nicholas Patrick
 */
public class Ui {
    /**
     * Prints a one line reply with the appropriate style.
     *
     * @param message A string of the one line reply message.
     */
    public static String reply(String message) {
        return "> " + message + '\n';
    }

    /**
     * Prints a multiline reply with the appropriate style.
     *
     * @param message An array of Strings containing the messages for each line.
     */
    public static String reply(String[] message) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < message.length; ++i) {
            ret.append(i == 0 ? "> " : "  ");
            ret.append(message[i]);
            ret.append('\n');
        }
        return ret.toString();
    }

    /**
     * Sends a goodbye message before closing dialogue.
     */
    public static String bye() {
        return reply("Bye. Hope to see you again soon!");
    }
}
