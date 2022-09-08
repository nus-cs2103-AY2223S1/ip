package duke;

/**
 * Represents the Parser, which deals with making sense of the user commands.
 */
public class Parser {

    /**
     * Reads the user command, and then does the required action.
     *
     * @param command  the user command.
     * @param tasklist the <code>TaskList</code> where tasks are stored.
     * @param ui       the ui to display messages to the user.
     */
    protected String commandParser(String command, TaskList tasklist, Ui ui) {
        if (command.equals("list")) {
            return tasklist.viewList(ui);
        } else if (command.matches("\\bmark\\s\\d+\\b")) {
            int num = Integer.parseInt(command.replaceAll("[^0-9]", ""));
            return tasklist.taskDone(num, ui);
        } else if (command.matches("\\bunmark\\s\\d+\\b")) {
            int num = Integer.parseInt(command.replaceAll("[^0-9]", ""));
            return tasklist.taskUndone(num, ui);
        } else if (command.matches("\\bdelete\\s\\d+\\b")) {
            int num = Integer.parseInt(command.replaceAll("[^0-9]", ""));
            return tasklist.deleteTask(num, ui);
        } else if (command.matches("\\bfind\\s.*\\b")) {
            String text = command.substring(5);
            return tasklist.findTask(text, ui);
        } else {
            return tasklist.addToList(command, ui);
        }
    }
}
