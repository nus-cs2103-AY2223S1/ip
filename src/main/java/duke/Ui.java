package duke;

/**
 * The Ui class encapsulates Duke responding to a user's input.
 */
public class Ui {
    public Ui() {}

    /**
     * Prints a message.
     * @param msg Message to be printed.
     */
    public String print(String msg) {
        return msg;
    }

    /**
     * Prints a message together with the number of tasks in the tasklist.
     * @param msg Message to be printed.
     * @param taskList Tasklist which number of tasks in it is to be printed.
     */
    public String print(String msg, TaskList taskList) {
        msg += String.format("Now, you have %d task(s) in the list.", taskList.length());
        return msg;
    }

    /**
     * Prints welcome message when a user logs on to Duke.
     */
    public String printWelcomeMsg() {
        String greeting = "Hello! I'm Duke\n";
        greeting += "What can I do for you?";
        return greeting;
    }
}
