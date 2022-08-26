package duke.exception;

/**
 * Exception representing invalid commands fed to application.
 *
 * @author WR3nd3
 */
public class DukeException extends IllegalArgumentException{

    private static final String INTRO = "Nyat a valid instruction! Rub my belly instead!\n";
    private static final String TODO = "Input 'todo ABC' to add task ABC\n";
    private static final String EVENT = "Input 'event ABC /at DATE' to add event ABC on" +
            " DATE\n";
    private static final String DEADLINE = "Input 'deadline ABC /by DATE' to add deadline" +
            " ABC due by DATE\n";
    private static final String MARK = "Input 'mark xxx' to mark task xxx as complete\n";
    private static final String UNMARK = "Input 'unmark xxx' to mark task xxx as incomplete\n";
    private static final String DELETE = "Input 'delete xxx' to delete task xxx from the list\n";
    private static final String LIST = "Input 'list' for overview\n";
    private static final String BYE = "Input 'bye' to exit.\n";
    private static final String OUTRO =  "NYAAAAAA!\n";
    private static final String TAB = "    ";

    /** Default error message for invalid commands */
    private static String defaultMsg = INTRO
            + TAB + TODO
            + TAB + EVENT
            + TAB + DEADLINE
            + TAB + MARK
            + TAB + UNMARK
            + TAB + DELETE
            + TAB + LIST
            + TAB + BYE
            + TAB + OUTRO;

    /**
     * Constructs duke.exception.DukeException object with default error message.
     */
    public DukeException() {
        super(defaultMsg);
    }

    /**
     * Constructs duke.exception.DukeException object with custom error message.
     *
     * @param message String representing error message to be displayed.
     */
    public DukeException(String message) {
        super(message);
    }



}
