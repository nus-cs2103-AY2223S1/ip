package duke;

/**
 * The Ui class encapsulates Duke responding to a user's input.
 */
public class Ui {
    private static final String separator = "===================================================";
    public Ui() {}

    /**
     * Prints a message.
     * @param msg Message to be printed.
     */
    public void print(String msg) {
        System.out.println(msg);
        System.out.println(separator);
    }

    /**
     * Prints a message together with the number of tasks in the tasklist.
     * @param msg Message to be printed.
     * @param taskList Tasklist which number of tasks in it is to be printed.
     */
    public void print(String msg, TaskList taskList) {
        msg += String.format("Now, you have %d task(s) in the list.", taskList.length());
        System.out.println(msg);
        System.out.println(separator);
    }

    /**
     * Prints welcome message when a user logs on to Duke.
     */
    public void printWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(separator);
    }
}
