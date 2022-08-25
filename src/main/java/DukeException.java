/**
 * Exception representing invalid commands fed to application.
 *
 * @author WR3nd3
 */
public class DukeException extends IllegalArgumentException{

    private static final String INTRO = "Nyat a valid instruction! Rub my belly instead!\n";
    private static final String TODO = "'todo ABC' to add task ABC\n";
    private static final String EVENT = "'event ABC /at DATE' to add event ABC on DATE\n";
    private static final String DEADLINE = "'deadline ABC /by DATE' to add deadline ABC due by DATE\n";
    private static final String MARK = "'mark x' to mark task x as complete\n";
    private static final String UNMARK = "'delete x' to delete task x from the list\n";
    private static final String DELETE = "'delete x' to delete task x from the list\n";
    private static final String LIST = "'list' for overview\n";
    private static final String BYE = "'bye' to exit.\n";
    private static final String OUTRO =  "NYAAAAAA!\n";
    private static final String TAB = "    ";

    private static String msg = INTRO
            + TAB + TODO
            + TAB + EVENT
            + TAB + DEADLINE
            + TAB + MARK
            + TAB + UNMARK
            + TAB + DELETE
            + TAB + LIST
            + TAB + BYE
            + TAB + OUTRO;


    public DukeException() {
        super(msg);
    }
    public DukeException(String message) {
        super(message);
    }



}
