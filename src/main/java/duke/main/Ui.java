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
        return "Hello! I'm Jukebox :)\n" + "What can I do for you today?";
    }

    /**
     * Returns the exit message.
     */
    public String exitJukebox() {
        return "Aww... OK, Hope to see you again!";
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
        return "Okay!\n" + "Added: " + task + "\n"
                + String.format("You now have %d task(s) in your task list!", taskList.getSize());
    }

    /**
     * Prints out message for marking and unmarking a task.
     * @param task Task that was marked or unmarked.
     * @param command Command that is either MARK or UNMARK.
     */
    public String markUnmarkTaskMessage(Task task, CommandWord command) throws DukeException {
        switch (command) {
        case MARK: {
            return "Goodjob! This task is now completed :)\n" + task;
        }
        case UNMARK: {
            return "Oh... OK, I'll mark this task as uncompleted!\n" + task;
        }
        default: {
            throw new DukeException("Hmm...I'm unable to mark/unmark this task...");
        }
        }
    }

    /**
     * Prints out message for deleting a task.
     * @param task Task that was deleted.
     * @param taskList Task list that the task was deleted from.
     */
    public String deleteTaskMessage(Task task, TaskList taskList) {
        return "Ok! I've removed this task: \n" + task;
    }
}
