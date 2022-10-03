package duke.ui;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Text UI of the application.
 */
public class Ui {
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String MESSAGE_GREET = "OMG HII! I am Floren the Best Assistant. What can I do for you?";
    private static final String MESSAGE_GOODBYE = "Hiks. I'm sad, but see you again!!";
    private static final String MESSAGE_INIT_FAILED = "Failed to initialise Duke application. Exiting...";
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";
    private static final String MESSAGE_EMPTY_LIST = "Your list is empty. Try adding what you wanna do!\n";
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";
    private static final String DIVIDER = "===================================================";
    private final Scanner in;

    public Ui() {
        this(System.in);
    }

    /**
     * Constructs Ui with specified InputStream and PrintStream
     */
    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        String fullInputLine = in.nextLine();

        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        return fullInputLine;
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void showWelcomeMessage() {
        showToUser(MESSAGE_GREET, DIVIDER);
    }

    public void showLine() {
        showToUser(DIVIDER);
    }

    /** Shows message(s) to the user */
    public String showToUser(String... message) {
        StringBuilder output = new StringBuilder();
        for (String m : message) {
            output.append(m);
        }
        return output.toString();
    }

    /**
     * Generates and prints the goodbye message at the end of the application.
     */
    public String showGoodbyeMessage() {
        return showToUser(MESSAGE_GOODBYE);
    }

    /**
     * Generates and prints the error message if there is an exception.
     */
    public String showErrorMessage(String errorMessage) {
        return showToUser(errorMessage);
    }

    /**
     * Generates and prints the error message if there is an error in initializing application.
     */
    public void showLoadingError() {
        showToUser(MESSAGE_INIT_FAILED);
    }

    /** Formats a list of strings as a viewable indexed list. */
    public static String getIndexedListForViewing(ArrayList<Task> taskItems) {
        if (taskItems.isEmpty()) {
            return MESSAGE_EMPTY_LIST;
        }
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = DISPLAYED_INDEX_OFFSET;
        for (Task taskItem : taskItems) {
            formatted.append(getIndexedListItem(displayIndex, taskItem.toString())).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String taskItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, taskItem);
    }

    /** Shows a list of strings to the user, formatted as an indexed list. */
    public String showToUserAsIndexedList(ArrayList<Task> taskItems) {
        return showToUser(getIndexedListForViewing(taskItems));
    }
}
