package duke;

import java.util.ArrayList;

class Ui {
    private static final String OPENING =
            "    Hello! I'm Duke\n    What can I do for you?\n    Type help for detailed useful guide.";
    private static final String ENDING = "    Bye. Hope to see you again soon!";
    private static final String ERROR_DEFAULT_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_UNFOUND_MESSAGE = "OOPS!!! There is no record matching your keyword :(";
    private static final String ERROR_PROCESS_MESSAGE =
            "OOPS!!! The command cannot be processed :( Type 'help' for more guides";
    private static final String ERROR_WRITE_MESSAGE =
            "OOPS!!! I fail to write in the file, possibly due to no permission. Please help me out :(";
    private static final String HELP_MESSAGE =
            "Sample instructions as follows:\nlist\ntodo sleep1\nevent sleep2 /at 2019-10-10\n"
            + "deadline sleep3 /by 2019-10-10\nmark 1\nunmark 1\ndelete 1\n\n\n"
            + "Currently, only yyyy-mm-dd format is supported such as 2019-10-10.";
    private static final String ENDED_SESSION_MESSAGE = "This program has terminated. "
        + "No input is taken any more. Please close and reopen program";

    /**
     * Prints the given string in a formatted manner.
     *
     * @param s string to be printed
     * @return String to be displayed to the user
     */
    protected static String formatPrint(String s) {
        return s;
    }

    /**
     * Prints the list of tasks in a detailed manner.
     *
     * @param taskList the list of tasks
     */
    protected static String listPrint(TaskList taskList) {
        String opening = "Here are the tasks in your list:\n";
        String[] separators = new String[] {"\n", ". "};
        ArrayList<Task> arr = taskList.getTaskArrayList();
        int count = 1;
        String result = opening;
        for (Task t : arr) {
            if (count != 1) {
                result += separators[0];
            }
            result += count + separators[1] + t.toString();
            count++;
        }
        return Ui.formatPrint(result);
    }

    /**
     * Greets the user.
     *
     * @return greeting string to be displayed to user
     */
    protected static String greet() {
        return Ui.formatPrint(OPENING);
    }

    /**
     * Prints help manual for the user.
     *
     * @return help string
     */
    protected static String help() {
        return Ui.formatPrint(HELP_MESSAGE);
    }

    /**
     * Says bye to the user.
     *
     * @return bye string to be displayed to user
     */
    protected static String bye() {
        return Ui.formatPrint(ENDING);
    }

    protected static String endedSessionPrint() {
        return Ui.formatPrint(ENDED_SESSION_MESSAGE);
    }

    /**
     * Prints output for no matching result case.
     */
    protected static String processUnfoundResult() {
        return ERROR_UNFOUND_MESSAGE;
    }

    /**
     * Prints output when task state has been changed.
     *
     * @param t task object to be modified on
     * @param isDone the state the task object is changed to
     * @return result string to be displayed to user
     */
    protected static String taskStateChangePrint(Task t, boolean isDone) {
        String successMsg = "Nice! I've marked this task as done:\n";
        String failMsg = "OK, I've marked this task as not done yet:\n";
        String res;
        if (isDone) {
            res = successMsg;
        } else {
            res = failMsg;
        }
        return Ui.formatPrint(res + t.toString());
    }

    /**
     * Processes the exception output.
     *
     * @param msg the error message to decide which scenario
     * @return result string to be displayed to user
     */
    protected static String processExceptionOutput(String msg) {
        switch (msg) {
        case "Unable to parse query":
            // fall through
        case "Unable to process query":
            return Ui.formatPrint(ERROR_PROCESS_MESSAGE);
        case "Unable to write the record":
            return Ui.formatPrint(ERROR_WRITE_MESSAGE);
        default:
            return Ui.formatPrint(ERROR_DEFAULT_MESSAGE);
        }
    }
}
