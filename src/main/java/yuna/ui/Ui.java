package yuna.ui;
import java.util.Scanner;

/**
 * Ui deals with interactions with the user.
 *
 * @author Bryan Ng Zi Hao
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Greets the user when the bot starts.
     *
     * @return the string representation of the greeting message.
     */
    public static String greet() {
        String output = " Hellloooo!\n";
        output += " How's your day going?! ^^";
        return output;
    }

    /**
     * Reads in whatever input the user provides.
     *
     * @return String format of what the user wrote.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }

    /**
     * Reads in whatever input the user provides.
     *
     * @param message is formatted.
     */
    public String formatMessage(String message) {
        return message;
    }
}
