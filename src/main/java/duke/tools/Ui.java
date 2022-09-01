package duke.tools;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * This class deals with interactions with the user.
 */
public class Ui {
    /**
     * Creates a greetings message.
     *
     * @return Greeting message.
     */
    public static String formatGreetingString() {
        return "Hello!\nHow may i help you today?";
    }

    /**
     * Creates a farewell message.
     *
     * @return Farewell message.
     */
    public static String formatFarewellString() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Creates a message of status of TaskList.
     * If TaskList is not empty, include all contents of TaskList.
     *
     * @param taskList List of task to be printed.
     * @return List status message.
     * @throws DukeException If taskList.getTask(i) takes in invalid index.
     */
    public static String formatListStatusString(TaskList taskList) throws DukeException {
        String output;
        if (taskList.getSize() == 0) {
            output = "There are currently no tasks in your list";
        } else {
            output = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.getSize(); i++) {
                output = output.concat(String.format("%d. %s\n", i + 1,
                        taskList.getTask(i)));
            }
            output.concat(String.format("Now you have %d tasks in the list.\n",
                    taskList.getSize()));
        }
        return output;
    }

    /**
     * Create a message of all task found containing keyword.
     *
     * @param taskList TaskList to search through with keyword.
     * @param keyword Keyword to find.
     * @return Find string message.
     * @throws DukeException If taskList.getTask(i) takes in invalid index.
     */
    public static String formatFindTaskString(TaskList taskList, String keyword) throws DukeException {
        String output;
        if (taskList.isEmpty()) {
            output = "There are currently no tasks in your list";
        } else {
            output = "Here are the matching tasks in your list:\n";
            int findCount = 0;
            for (int i = 0; i < taskList.getSize(); i++) {
                if (taskList.getTask(i).isFoundInDescription(keyword)) {
                    output = output.concat(String.format("%d. %s\n", i + 1,
                            taskList.getTask(i)));
                    findCount++;
                }
            }
            if (findCount == 0) {
                output = output.concat("Oh no, there are no matching tasks found :(");
            } else {
                output = output.concat(String.format("There are %d matching tasks found\n",
                        findCount));
            }
        }
        return output;
    }

    /**
     * Creates a message marking task as done in TaskList.
     *
     * @param index Index of task that is marked.
     * @param task Task marked as done.
     * @return Message.
     */
    public static String formatMarkAsDoneString(int index, Task task) {
        return String.format("Nice! I've marked this task as done:\n%d. %s",
                index + 1, task);
    }

    /**
     * Prints message marking task as undone in TaskList.
     *
     * @param index Index of task that is marked.
     * @param task Task marked as undone.
     * @return Message.
     */
    public static String formatMarkAsUndoneString(int index, Task task) {
        return String.format("Ok! I've marked this task as not done yet:\n%d. %s",
                index + 1, task);
    }

    /**
     * Creates a message of deleting task from TaskList.
     *
     * @param index Index of task that is deleted.
     * @param task Task that was deleted.
     * @return Message.
     */
    public static String formatDeleteTaskString(int index, Task task) {
        return String.format("Noted. I've removed this task:\n%d. %s",
                index + 1, task);
    }

    /**
     * Creates a message that TaskList is empty and no task can be deleted.
     */
    public static String formatNoTaskToDeleteString() {
        return "OOPS!!! There are currently no task to delete";
    }

    /**
     * Creates a message of adding task to TaskList.
     *
     * @param task Task that was added.
     * @return Message.
     */
    public static String formatAddTaskString(Task task) {
        return "Got it! I've added this task:\n> " + task;
    }

    /**
     * Creates a message of DukeException that occurred.
     *
     * @param e DukeException containing error message.
     * @return Error message.
     */
    public static String formatExceptionString(DukeException e) {
        return e.getMessage();
    }
}
