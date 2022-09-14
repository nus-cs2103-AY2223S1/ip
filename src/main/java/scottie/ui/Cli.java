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

    private static final String WELCOME_MESSAGE = "Oh, hey buddy! I'm Scottie!\n"
            + "What's up?";

    private static final String ERROR_COLOUR = "\033[0;31m"; // ANSI for red colour
    private static final String NO_COLOUR = "\033[0m"; // ANSI for no colour

    private final Scanner in;
    private final PrintStream out;

    private boolean isProgramEnded;

    /**
     * Constructs a CLI with the given InputStream and PrintStream.
     *
     * @param in The InputStream to read user input from.
     * @param out The PrintStream to write to.
     */
    public Cli(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
        this.isProgramEnded = false;
    }

    /**
     * Reads a single line of input from the user.
     *
     * @return A line of input from the user.
     */
    public String readLine() {
        String line = in.nextLine();
        while (line.isBlank()) {
            line = in.nextLine();
        }
        return line;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessages(String... messages) {
        for (String message : messages) {
            this.out.println(message);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showFormattedMessage(String message, Object... args) {
        this.showMessages(String.format(message, args));
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
            this.showFormattedMessage("%d. %s", i, obj);
            i++;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showError(String errorMessage) {
        this.showMessages(ERROR_COLOUR + errorMessage + NO_COLOUR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showFormattedError(String message, Object... args) {
        this.showError(String.format(message, args));
    }

    /**
     * Ends communication with the user.
     */
    @Override
    public void endProgram() {
        in.close();
        out.close();
        this.isProgramEnded = true;
    }

    /**
     * Returns whether the program has ended.
     *
     * @return whether the program has ended.
     */
    public boolean isProgramEnded() {
        return this.isProgramEnded;
    }
}
