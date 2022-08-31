package scottie.ui;

/**
 * An interface that allows communication to the user.
 */
public interface Ui {
    /**
     * Displays the given messages to the user.
     *
     * @param messages The messages to show the user.
     */
    void showMessages(String... messages);

    /**
     * Formats and displays the given message to the user.
     *
     * @param message The message to format and display.
     * @param args The arguments to be interpolated into the message.
     */
    void showFormattedMessage(String message, Object... args);

    /**
     * Displays the startup message to the user.
     */
    void showStartupMessage();

    /**
     * Displays an ordered list of items to the user.
     * Each item in the list will be labeled with a number
     * counting up from 1.
     *
     * @param iterable The items to be displayed to the user.
     */
    void showOrderedList(Iterable<?> iterable);
}
