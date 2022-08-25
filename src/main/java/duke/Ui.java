package duke;

/**
 * Contains methods to print information to user's UI.
 */
public class Ui {

    /**
     * Prints a greeting as well as important information relevant to operation to
     * standard output.
     */
    public void greet() {
        System.out.println("Hi, my name is Duke and it's power hour!");
        System.out.println("Commands are CASE SENSITIVE: enter all commands in lowercase.");
        System.out.println("Please input all dates and times in the following format:");
        System.out.println("dd-MM-yyyy-HH-mm (e.g. 23-04-2000-23-04)");
        System.out.println("********************************************");
    }

    /**
     * Prints a confirmation that the given task has been added to array.
     * 
     * @param task The task that has been added.
     */
    public void taskAddedMessage(Task task) {
        System.out.println("Task added: " + task);
    }

    /**
     * Prints a confirmation that the given task has been deleted from array.
     * 
     * @param task The task that has been deleted.
     */
    public void taskDeletedMessage(Task task) {
        System.out.println("Task removed: " + task);
    }

    /**
     * Prints a confirmation that the given task has been marked done.
     * 
     * @param task The task that has been marked done.
     */
    public void taskMarkedMessage(Task task) {
        System.out.println("Task marked as done: " + task);
    }

    /**
     * Prints a confirmation that the given task has been marked undone.
     * 
     * @param task The task that has been marked undone.
     */
    public void taskUnmarkedMessage(Task task) {
        System.out.println("Task marked as not done: " + task);
    }

    /**
     * Prints the size of the current task array.
     * 
     * @param size The size of the current task array.
     */
    public void taskListSizeMessage(int size) {
        if (size == 1) {
            System.out.println("There is " + size + " task in your list.");
        } else {
            System.out.println("There are " + size + " tasks in your list.");
        }
    }
}
