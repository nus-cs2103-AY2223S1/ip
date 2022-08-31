package scottie.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Encapsulates methods for reading from an InputStream and
 * printing to a PrintStream to interact with a user.
 */
public class Cli implements Ui {
    private static final String LOGO = " ____            _   _   _\n"
            + "/ ___|  ___ ___ | |_| |_(_) ___\n"
            + "\\___ \\ / __/ _ \\| __| __| |/ _ \\\n"
            + " ___) | (_| (_) | |_| |_| |  __/\n"
            + "|____/ \\___\\___/ \\__|\\__|_|\\___|\n";

    private static final String WELCOME_MESSAGE = "Hello there! I'm Scottie!\n"
            + "How can I help you?";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a CLI with the given InputStream and PrintStream.
     *
     * @param in The InputStream to read user input from.
     * @param out The PrintStream to write to.
     */
    public Cli(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Reads a single line of input from the user.
     *
     * @return A line of input from the user.
     */
    public String readLine() {
        return in.nextLine();
    }

    /**
     * {@inheritDoc}
     */
    public void showMessages(String... messages) {
        for (String message : messages) {
            this.out.println(message);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void showFormattedMessage(String message, Object... args) {
        this.out.printf(message, args);
    }

    /**
     * {@inheritDoc}
     */
    public void showStartupMessage() {
        this.showMessages(LOGO, WELCOME_MESSAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showOrderedList(Iterable<?> iterable) {
        int i = 1;
        for (Object obj : iterable) {
            this.showFormattedMessage("%d. %s%n", i, obj);
            i++;
        }
    }
}
