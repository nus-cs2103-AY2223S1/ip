package duke;

import duke.task.Task;

/**
 * UI interactions from the Duke program.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Ui {

    /**
     * Prints a farewell message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a notification that a task has been added to the list
     *
     * @param t Task that was added to the list.
     * @param len New length of the list.
     */
    public String showAdd(Task t, int len) throws DukeException {
        String openingMessage = "Got it. I've added this task:\n";
        String taskDescription = t.toString() + "\n";
        String closingMessage = "Now you have " + len + taskSingularOrPluralWord(len) + "in the list.";
        return openingMessage + taskDescription + closingMessage;
    }

    /**
     * Prints a notification that a task has been deleted from the list
     *
     * @param t Task that was deleted from the list.
     * @param len New length of the list.
     */
    public String showDelete(Task t, int len) throws DukeException {
        String openingMessage = "Noted. I've removed this task:\n";
        String taskDescription = t.toString() + "\n";
        String tasksLeft = "Now you have " + len + taskSingularOrPluralWord(len) + "in the list.";
        return openingMessage + taskDescription + tasksLeft;
    }

    /**
     * Prints a notification that a task has been marked as done.
     *
     * @param t Task that was marked as done.
     */
    public String showMark(Task t) {
        return "Nice! I've marked this task as done:\n" + t;
    }

    /**
     * Prints a notification that a task has been unmarked as done.
     *
     * @param t Task that was unmarked as done.
     */
    public String showUnmark(Task t) {
        return "OK, I've marked this task as not done yet:\n" + t;
    }

    /**
     * Prints a notification of tasks being found.
     */
    public String showFound() {
        return "Here are the matching tasks in your list:\n";
    }

    private String taskSingularOrPluralWord(int len) throws DukeException {
        boolean isSingular = len == 0 || len == 1;
        boolean isPlural = len >= 2;
        String word;

        if (isSingular) {
            word = " task ";
        } else if (isPlural) {
            word = " tasks ";
        } else {
            String errorMessage = "Length of task list must not be non-negative.";
            throw new DukeException(errorMessage);
        }

        return word;
    }
}
