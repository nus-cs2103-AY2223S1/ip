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
        ArrayList<Task> arr = taskList.getTaskArrayList();
        int count = 1;
        String result = "Here are the tasks in your list:\n";
        for (Task t : arr) {
            if (count != 1) {
                result += "\n";
            }
            result += "    " + count + ". " + t.toString();
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
        // Todo: figure out how to greet first
        return Ui.formatPrint(OPENING);
    }

    /**
     * Says bye to the user.
     *
     * @return bye string to be displayed to user
     */
    protected static String bye() {
        return Ui.formatPrint(ENDING);
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
        String res;
        if (isDone) {
            res = "Nice! I've marked this task as done:\n";
        } else {
            res = "OK, I've marked this task as not done yet:\n";
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
            // flow through
        case "Unable to process query":
            return Ui.formatPrint(ERROR_PROCESS_MESSAGE);
        case "Unable to write the record":
            return Ui.formatPrint(ERROR_WRITE_MESSAGE);
        default:
            return Ui.formatPrint(ERROR_DEFAULT_MESSAGE);
        }
    }
}
