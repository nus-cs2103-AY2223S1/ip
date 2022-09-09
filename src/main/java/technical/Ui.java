package technical;

import java.util.Scanner;

/**
 * The class for interacting with the user.
 * @author Nicholas Patrick
 */
public class Ui {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Reads a line.
     *
     * @return A String containing the line.
     */
    public static String readLine() {
        return scanner.nextLine();
    }

    /**
     * Prints a one line reply with the appropriate style.
     *
     * @param message A string of the one line reply message.
     */
    public static void reply(String message) {
        System.out.print("> ");
        System.out.println(message);
    }

    /**
     * Prints a multiline reply with the appropriate style.
     *
     * @param message An array of Strings containing the messages for each line.
     */
    public static void reply(String[] message) {
        for (int i = 0; i < message.length; ++i) {
            System.out.print(i == 0 ? "> " : "  ");
            System.out.println(message[i]);
        }
    }

    /**
     * Sends a goodbye message before closing dialogue.
     */
    public static void bye() {
        reply("Bye. Hope to see you again soon!");
    }
}
