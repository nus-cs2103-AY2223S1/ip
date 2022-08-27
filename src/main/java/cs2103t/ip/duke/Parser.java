package cs2103t.ip.duke;

public class Parser {

    /**
     * Checks if task input is of type to do
     * @param input
     * @return True if task is of type to do, false if it is not.
     */
    public boolean isTodo(String input) {
        return input.startsWith("todo");
    }

    /**
     * Checks if task input is of type event
     * @param input
     * @return True if task is of type event, false if it is not.
     */

    public boolean isEvent(String input) {
        return input.startsWith("event");
    }

    /**
     * Checks if task input is of type deadline
     * @param input
     * @return True if task is of type deadline, false if it is not.
     */
    public boolean isDeadline(String input) {
        return input.startsWith("deadline");
    }

    /**
     * Checks if input begins with "mark" which signifies that a task is to be marked
     * as complete.
     * @param input
     * @return True if input begins with "mark".
     */

    public boolean isMark(String input) {
        return input.startsWith("mark");
    }

    /**
     * Checks if input begins with "unmark" which signifies that a task is to be marked
     * as incomplete.
     * @param input
     * @return True if input begins with "unmark".
     */

    public boolean isUnmark(String input) {
        return input.startsWith("unmark");
    }

    /**
     * Checks if input begins with "delete" which signifies that a task should be deleted.
     * @param input
     * @return True if input begins with "delete".
     */

    public boolean isDelete(String input) {
        return input.startsWith("delete");
    }

    /**
     * Checks if input begins with "bye" which signifies that the user wants to close the bot.
     * @param input
     * @return True if input begins with "bye".
     */

    public boolean isBye(String input) {
        return input.startsWith("bye");
    }

    /**
     * Checks if input begins with "list" which signifies that the list of tasks should be displayed.
     * @param input
     * @return True if input begins with "list".
     */
    public boolean isList(String input) {
        return input.startsWith("list");
    }

    public boolean isFind(String input) {
        return input.startsWith("find");
    }
}
