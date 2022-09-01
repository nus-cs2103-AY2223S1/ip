package duke.main;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Class encapsulating user interaction output.
 */
public class Ui {

    /**
     * Constructor for the Ui class.
     */
    public Ui() {

    }

    /**
     * Outputs greeting message to user.
     */
    public String greetUser() {
        return "\tHey there! I'm Tutter! \n\tHow can I help?";
    }

    /**
     * Outputs goodbye message to user.
     */
    public String sayGoodbye() {
        return "\tGoodbye!";
    }

    /**
     * Outputs error message when
     */
    public String showLoadingError() {
        return "\n\tLooks like I can't find your old task list..."
                + "\n\tGuess we'll have to start a new one!\n";
    }

    public String printErrorMessage(DukeException de) {
        return de.getMessage();
    }

    /**
     * Outputs success message to user when task is added successfully.
     *
     * @param task Task object that was added to the task list.
     * @param size Number of tasks in the task list.
     */
    public String displayTaskAddedMessage(Task task, int size) {
        if (task != null) {
            String output = String.format("\tYou have added \"%s\" into your Task List!\n"
                    + "\tYou have %d tasks in your Task List!", task, size);
            return output;
        }
        return "";
    }

    /**
     * Outputs success message to user when a task is marked or unmarked successfully.
     *
     * @param task    Task to be marked or unmarked.
     * @param command Keyword corresponding to mark or unmark.
     */
    public String displayTaskMarkUnmarkMessage(Task task, Keyword command) throws DukeException {
        switch (command) {
        case MARK: {
            String taskListString = String.format("\tGood Job! The following task "
                    + "has been marked as done:\n\t%s", task);
            return taskListString;
        }
        case UNMARK: {
            String taskListString = String.format("\tOkay! The following task "
                    + "has been marked as not done:\n\t%s", task);
            return taskListString;
        }
        default: {
            throw new DukeException("Unexpected Error in displayTaskMarkUnmarkMessage!");
        }
        }
    }

    /**
     * Outputs task deleted message to user when task is deleted successfully.
     *
     * @param task Task to be deleted.
     * @param size Size of task list after deleting task.
     */
    public String displayTaskDeletedMessage(Task task, int size) {
        String output = String.format("\tYou have deleted \"%s\" into your Task List!\n"
                + "\tYou have %d tasks in your Task List!", task, size);
        return output;
    }

}
