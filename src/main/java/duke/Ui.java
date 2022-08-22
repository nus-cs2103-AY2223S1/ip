/**
 * This class handles all main interactions with the user, including
 * outputting any error messages if any.
 */

public class Ui {

    public Ui() {
    }

    /**
     * Outputs a salutation, indicating the programme has started running.
     */
    public void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Outputs a sign-off, indicating the programme has ended.
     */
    public void close() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Outputs any error message, which briefly describes its origin, and ways
     * to fix it.
     * @param e DukeException
     */
    public void showError(DukeException e) {
        System.out.println(e);
    }

    /**
     * Outputs the string representation of all tasks that has been
     * added to the arraylist via the Task toString method.
     * @param tasks
     */
    public void printList(TaskList tasks) {
        Task[] inputArray = tasks.taskListToArray();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= inputArray.length; i++) {
            String item = String.format("%s. %s", i, inputArray[i-1].toString());
            System.out.println(item);
        }
    }
}
