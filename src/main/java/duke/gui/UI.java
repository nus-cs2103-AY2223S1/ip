package duke.gui;

import duke.Command;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * This class handles the interactions with the user.
 */
public class UI {

    /**
     * Displays the Greeting message.
     */
    public static String greet() {
        return "Greetings from Elp!\nWhat can I help you with?\n";
    }

    /**
     * Prints Goodbye Message.
     */
    public static String goodbye() {
        return "Have a nice day! :)";
    }

    /**
     * Prints the error given when no index is given when deleting tasks.
     */
    public static String printDeleteErrorMessage() {
        return "Please add an index to delete a task!\n";
    }

    /**
     * Prints the exception message when a DukeException is thrown.
     *
     * @param e DukeException thrown.
     */
    public static String printDukeExceptionMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * Prints the message when a Task is added to the list.
     *
     * @param t task added.
     */
    public static String printAddTaskMessage(Task t) {
        return "Added: " + t.toString() + "\n";
    }

    /**
     * Prints the message when a task has been marked/unmarked.
     *
     * @param task    Task to be marked.
     * @param command Mark status.
     * @return String output of the marked/unmarked task.
     */
    public static String printMarkedTaskMessage(Task task, Command command) throws DukeException {
        switch (command) {
        case MARK: {
            return "Task successfully marked!\n" + task;
        }
        case UNMARK: {
            return "Task successfully unmarked!\n" + task;
        }
        default: {
            throw new DukeException("Error in printing marked task!");
        }
        }
    }

    /**
     * Prints the message if the task list is empty.
     *
     * @return Empty list message.
     */
    public static String printTaskListEmpty() {
        return "No tasks have been added!";
    }
}
