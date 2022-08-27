package duke;

/**
 * The Class that deals with user interactions.
 *
 * @author Denzel Tan
 */
public class Ui {
    /**
     * Prints the starting message.
     */
    public static void sayGreeting() {
        System.out.println("Hello! I'm duke.Duke\n" + "What can I do for you?\n");
    }


    /**
     * Prints the end message.
     */
    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    /**
     * Prints out the message when a task has been marked as done.
     *
     * @param task the task to be marked as done
     */
    public static void markedDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  " + task.toString());
    }


    /**
     * Prints out the message when a task has been marked as undone.
     *
     * @param task the task to be marked as undone
     */
    public static void markUndoneMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n  " + task.toString());
    }


    /**
     * Prints out the message when there is an error loading the file.
     *
     * @param str the name of the file that has error loading
     */
    public static void showLoadingError(String str) {
        System.out.println("Loading error! New file: " + str + " created, please continue and type in a new task!");
    }


    /**
     * Prints out the message when the file exists.
     *
     * @param str the name of the file that exists
     */
    public static void showExistingFile(String str) {
        System.out.println("File " + str + " exists, please continue and type in a task!");
    }


    /**
     * Prints the starting message when list is called.
     */
    public static void printListStartingMessage() {
        System.out.println("Here are the tasks in your list:");
    }


    /**
     * Prints the current task.
     *
     * @param i the index of the task to print
     * @param currTask the task to print
     */
    public static void printTask(int i, Task currTask) {
        System.out.println(i + ". " + currTask);
    }

    /**
     * Print the starting message output when finding tasks.
     */
    public static void printFoundTasksStart() {
        System.out.println("Here are the matching tasks in your list:");
    }
}
