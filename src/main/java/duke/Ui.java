package duke;

/**
 * Handles interaction with user.
 */
public class Ui {
    /**
     * Prints message with line format.
     *
     * @param message message to be printed.
     */
    public void printWithLineFormat(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.println(message);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Greets user.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printWithLineFormat("     Hello! I'm Duke\n"
                + "     What can I do for you?\n");
    }

    /**
     * Prints Task information when added.
     *
     * @param task Task that was added.
     * @param size Current number of tasks in list.
     *
     */
    public void printAddTask(Task task, int size) {
        printWithLineFormat("     Got it. I've added this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints upon deletion of Task.
     *
     * @param task Task deleted.
     * @param size Remaining number of Tasks in list.
     */
    public void printDeleteTask(Task task, int size) {
        printWithLineFormat("     Noted. I've removed this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints list of Tasks currently.
     *
     * @param list The list of Tasks in String.
     */
    public void printDisplayList(String list) {
        String message = "     Here are the tasks in your list:\n";
        message += list;
        printWithLineFormat(message);
    }

    /**
     * Prints when task is marked.
     *
     * @param message The Task that was marked in String.
     */
    public void printMarkTask(String message) {
        printWithLineFormat("     Nice! I've marked this task as done:\n"
                + "       " + message);
    }

    /**
     * Prints when task is unmarked.
     *
     * @param message The Task that was unmarked in String.
     */
    public void printUnmarkTask(String message) {
        printWithLineFormat("     OK, I've marked this task as not done yet:\n"
                + "       " + message);
    }

    /**
     * Prints when program terminates.
     */
    public void printExit() {
        printWithLineFormat("     Bye. Hope to see you again soon!");
    }
}
