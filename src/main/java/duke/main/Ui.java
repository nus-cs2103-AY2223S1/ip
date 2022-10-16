package duke.main;

import duke.commandword.CommandWord;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Class encapsulating user interactions.
 */
public class Ui {
    /**
     * Constructor for the Ui class.
     */
    public Ui() {
    }

    /**
     * Returns the greeting message
     */
    public static String greetUser() {
        return "Meow! I'm Toothless :)\n" + "What can I do for you today?";
    }

    /**
     * Returns the exit message.
     */
    public String exitJukebox() {
        return "Purr...Ok, Hope to see you again!";
    }

    /**
     * Prints out error message of DukeException.
     * @param de DukeException of which message to print.
     */
    public String printErrorMessage(DukeException de) {
        return de.getMessage();
    }

    /**
     * Prints out message for a successful loading of save file.
     */
    public String successLoadMessage() {
        return "Loading save file... Done!";
    }

    /**
     * Prints out message for adding a task.
     * @param task Task that was added into the task list.
     * @param taskList Task list that the task was added into.
     */
    public String addTaskMessage(Task task, TaskList taskList) {
        return "Meow!\n" + "Added: " + task + "\n"
                + String.format("You meow have %d task(s) in your task list!", taskList.getSize());
    }

    /**
     * Prints out message for marking and unmarking a task.
     * @param task Task that was marked or unmarked.
     * @param command Command that is either MARK or UNMARK.
     */
    public String markUnmarkTaskMessage(Task task, CommandWord command) throws DukeException {
        switch (command) {
        case MARK: {
            return "Goodjob! This task is meow completed :)\n" + task;
        }
        case UNMARK: {
            return "Oh... OK, I'll meow-rk this task as uncompleted!\n" + task;
        }
        default: {
            throw new DukeException("Purr...I'm unable to mark/unmark this task...");
        }
        }
    }

    /**
     * Prints out message for deleting a task.
     * @param task Task that was deleted.
     * @param taskList Task list that the task was deleted from.
     */
    public String deleteTaskMessage(Task task, TaskList taskList) {
        return "Meow! I've re-meow-ved this task: \n" + task;
    }

    public String addedNote(String note) {
        return "Meow! I've added \"" + note + "\" to the task!";
    }
}
