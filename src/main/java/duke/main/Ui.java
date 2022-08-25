package duke.main;

import duke.exception.DukeException;
import duke.main.Keyword;
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
    public void greetUser() {
        System.out.println("\tHey there! I'm Tutter! \n\tHow can I help?");
    }

    /**
     * Outputs goodbye message to user.
     */
    public void sayGoodbye() {
        System.out.println("\tGoodbye!");
    }

    /**
     * Outputs error message when
     */
    public void showLoadingError() {
        System.out.print("\n\tLooks like I can't find your old task list..." +
                "\n\tGuess we'll have to start a new one!\n");
    }

    public void printErrorMessage(DukeException de) {
        System.out.println(de.getMessage());
    }

    /**
     * Outputs success message to user when task is added successfully.
     *
     * @param task Task object that was added to the task list.
     * @param size Number of tasks in the task list.
     */
    public void displayTaskAddedMessage(Task task, int size) {
        if (task != null) {
            String output = String.format("\tYou have added \"%s\" into your Task List!\n" +
                    "\tYou have %d tasks in your Task List!", task, size);
            System.out.println(output);
        }
    }

    /**
     * Outputs success message to user when a task is marked or unmarked successfully.
     *
     * @param task    Task to be marked or unmarked.
     * @param command Keyword corresponding to mark or unmark.
     */
    public void displayTaskMarkUnmarkMessage(Task task, Keyword command) {
        switch (command) {
        case MARK: {
            String taskListString = String.format("\tGood Job! The following task " +
                    "has been marked as done:\n\t%s", task);
            System.out.println(taskListString);
            break;
        }
        case UNMARK: {
            String taskListString = String.format("\tOkay! The following task " +
                    "has been marked as not done:\n\t%s", task);
            System.out.println(taskListString);
            break;
        }
        }
    }

    /**
     * Outputs task deleted message to user when task is deleted successfully.
     *
     * @param task Task to be deleted.
     * @param size Size of task list after deleting task.
     */
    public void displayTaskDeletedMessage(Task task, int size) {
        String output = String.format("\tYou have deleted \"%s\" into your Task List!\n" +
                "\tYou have %d tasks in your Task List!", task, size);
        System.out.println(output);
    }

}
