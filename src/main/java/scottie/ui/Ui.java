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

    /**
     * Displays the given error message to the user.
     *
     * @param errorMessage The error message to show to the user.
     */
    default void showError(String errorMessage) {
        this.showMessages(errorMessage);
    }

    /**
     * Formats and displays the given error message to the user.
     *
     * @param message The error message to format and display.
     * @param args The arguments to be interpolated into the error message.
     */
    default void showFormattedError(String message, Object... args) {
        this.showFormattedMessage(message, args);
    }

    /**
     * Signal to this Ui that the application should be ended.
     */
    void endProgram();
}
