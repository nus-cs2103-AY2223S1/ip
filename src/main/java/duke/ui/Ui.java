package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Represents a controller that handles output for a user.
 */
public class Ui {

//    final String HORIZONTAL_LINE = "______________________________________\n";
//
//    /**
//     * Print to system out string encapsulated within horizontal lines.
//     *
//     * @param str String to be printed
//     */
//    public void formatPrint(String str) {
//        System.out.println(HORIZONTAL_LINE + str + "\n" + HORIZONTAL_LINE);
//    }

    /**
     * Prints the introductory message to the user.
     */
    public String printIntroduction() {
        String firstText = "hi... I'm Karen\nWhat do you want this time?";
        return firstText;
    }

    /**
     * Prints the concluding message to the user.
     */
    public String printByeMessage() {
        return "K finally, good riddance!";
    }

    /**
     * Prints the tasks stored inside the task list.
     *
     * @param list TaskList that contains tasks
     */
    public String printListMessage(TaskList list) {
        if (list.getSize() == 0) {
            return "pff there is nothing in your list";
        } else {
            String str = "Here are your dumb tasks in your list:\n";
            for (int i = 1; i < list.getSize() + 1; i++) {
                Task item = list.getTask(i - 1);
                str += i + ". " + item.toString() + "\n";
            }
            return str;
        }
    }

    /**
     * Prints the mark message to the user.
     *
     * @param task Task that was marked
     */
    public String printMarkMessage(Task task) {
        String str = "Took you long enough to complete this duke.task:\n" + task.toString();
        return str;
    }

    /**
     * Prints the unmark message to the user.
     *
     * @param task Task that was unmarked
     */
    public String printUnmarkMessage(Task task) {
        String str = "Another duke.task marked as not done?? Slow indeed\n" + task.toString();
        return str;
    }

    public String printAddTaskMessage(Task task, int size) {
        String str = "Fine, I'll add this duke.task:\n\t" + task.toString() + "\nNow you have "
                + size + " tasks in the list...";
        return str;
    }

    public String printDeleteMessage(Task task, int size) {
        String str = "Ughh I'll remove this duke.task:\n\t" + task.toString() + "\nNow you have "
                + size + " tasks in the list...";
        return str;
    }

    public String printUnknownMessage() {
        return "What are you saying??? Try again";
    }

    public String printErrorMessage(String str) {
        return str;
    }

    public String showLoadingError() {
        return "Error loading data from file";
    }

    public String printFindMessage(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return "HAHA unlucky! There is no matching task in your list!";
        } else {
            String str = "Tsk! Be grateful I searched through your entire list to find these:\n";
            for (int i = 1; i < taskList.getSize() + 1; i++) {
                Task item = taskList.getTask(i - 1);
                str += i + ". " + item.toString() + "\n";
            }
            return str;
        }
    }
}
