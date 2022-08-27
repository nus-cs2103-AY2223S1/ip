package duke;

public class Ui {
    public void printWithLineFormat(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.println(message);
        System.out.println("    ____________________________________________________________");
    }
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

    public void printAddTask(Task task, int size) {
        printWithLineFormat("     Got it. I've added this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + size + " tasks in the list.");
    }

    public void printDeleteTask(Task task, int size) {
        printWithLineFormat("     Noted. I've removed this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + size + " tasks in the list.");
    }

    public void printDisplayList(String list) {
        String message = "     Here are the tasks in your list:\n";
        message += list;
        printWithLineFormat(message);
    }

    public void printMarkTask(String message) {
        printWithLineFormat("     Nice! I've marked this task as done:\n"
                + "       " + message);
    }

    public void printUnmarkTask(String message) {
        printWithLineFormat("     OK, I've marked this task as not done yet:\n"
                + "       " + message);
    }

    /**
     * Prints Tasks that matched description given.
     *
     * @param list Tasks that matched description given.
     */
    public void printFindTask(String list) {
        String message = "     Here are the matching tasks in your list:\n";
        message += list;
        printWithLineFormat(message);
    }

    public void printExit() {
        printWithLineFormat("     Bye. Hope to see you again soon!");
    }
}
