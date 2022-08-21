package duke.ui;

import duke.commands.CommandResult;

/**
 * Represents the UI in the Duke application.
 * Responsible for displaying text output to the user.
 */
public class Ui {
    private static final String INDENTATION = "    ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Show messages to the user.
     *
     * @param messages List of messages to be shown.
     */
    public void show(String... messages) {
        for (String m : messages) {
            // Formats message.
            System.out.println(INDENTATION + m.replace("\n", LINE_SEPARATOR + INDENTATION));
        }
    }

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcomeMessage() {
        this.show(
                HORIZONTAL_LINE,
                "Hello I'm",
                "____        _        ",
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "",
                "What can I do for you?",
                HORIZONTAL_LINE
        );
    }

    /**
     * Shows the goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        this.show(
                "Bye, hope to see you soon!",
                HORIZONTAL_LINE
        );
    }

    /**
     * Shows an error message to the user.
     *
     * @param message Error message to be shown to the user.
     */
    public void showErrorMessage(String message) {
        this.show(
                message,
                HORIZONTAL_LINE
        );
    }

    /**
     * Shows an exception's message to the user.
     *
     * @param exception Exception to be shown to the user.
     */
    public void showErrorMessage(Exception exception) {
        this.showErrorMessage(exception.getMessage());
    }

    /**
     * Shows a result's message to the user.
     *
     * @param result Result to be shown to the user.
     */
    public void showResult(CommandResult result) {
        this.show(result.getUserMessage(), HORIZONTAL_LINE);
    }
}
