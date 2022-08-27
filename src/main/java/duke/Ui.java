package duke;

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


    public static void markedDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  " + task.toString());
    }

    public static void markUndoneMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n  " + task.toString());
    }

    public static void showLoadingError(String str) {
        System.out.println("Loading error! New file: " + str + " created, please continue and type in a new task!");
    }

    public static void showExistingFile(String str) {
        System.out.println("File " + str + " exists, please continue and type in a task!");
    }

    public static void printListStartingMessage() {
        System.out.println("Here are the tasks in your list:");
    }

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
