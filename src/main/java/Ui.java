import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String MESSAGE_GREET = "OMG HII! I am Floren! What can I do for you?";
    private static final String MESSAGE_GOODBYE = "Hiks. I'm sad, but see you again!!";
    public static final String MESSAGE_INIT_FAILED = "Failed to initialise Duke application. Exiting...";
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";
    private static final String MESSAGE_EMPTY_LIST = "Your list is empty. Try adding what you wanna do!\n";
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";
    private static final String DIVIDER = "===================================================";
    private static final String LS = System.lineSeparator();
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    public String getUserCommand() {
        String fullInputLine = in.nextLine();

        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        return fullInputLine;
    }

    public void showWelcomeMessage() {
        showToUser(MESSAGE_GREET, DIVIDER);
    }

    public void showLine() {
        showToUser(DIVIDER);
    }

    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m.replace("\n", LS));
        }
    }

    public void showGoodbyeMessage() {
        showToUser(MESSAGE_GOODBYE);
    }

    public void showErrorMessage(String errorMessage) {
        showToUser(errorMessage);
    }

    public void showLoadingError() {
        showToUser(MESSAGE_INIT_FAILED);
    }

    public static String getIndexedListForViewing(ArrayList<Task> taskItems) {
        if (taskItems.isEmpty()) {
            return MESSAGE_EMPTY_LIST;
        }
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (Task taskItem : taskItems) {
            formatted.append(getIndexedListItem(displayIndex, taskItem.toString())).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    private static String getIndexedListItem(int visibleIndex, String taskItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, taskItem);
    }

    public void showToUserAsIndexedList(ArrayList<Task> taskItems) {
        showToUser(getIndexedListForViewing(taskItems));
    }
}
