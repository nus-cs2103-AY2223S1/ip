package duke;

import java.util.Scanner;

/**
 * Ui class containing logic for receiving user input and printing output
 */
public class Ui {

    private static final String GREETINGS = "Hello! I'm Ekud \n" + "What can I do for you?";
    private static final String BANNER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private Scanner sc;

    /**
     * Creates new Ui class.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads a line of user input.
     *
     * @return String containing user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a string with formatting.
     *
     * @param msg String to be printed.
     */
    public void print(String msg) {
        System.out.println(BANNER);
        System.out.println(msg);
        System.out.println(BANNER);
    }

    /**
     * Prints greetings message.
     */
    public void printGreetings() {
        print(GREETINGS);
    }

    /**
     * Prints message when task is added.
     * @param msg Description of task added.
     * @param size Size of task list.
     */
    public void printAddTask(String msg, int size) {
        print("Got it. I've added this task:\n" + msg
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints entire task list.
     *
     * @param tasks TaskList to be printed.
     */
    public void printList(TaskList tasks) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            list += (i + 1) + "." + tasks.get(i);
            if (i != tasks.size() - 1) {
                list += "\n";
            }
        }
        print(list);
    }

    /**
     * Closes system input.
     */
    public void close() {
        sc.close();
    }
}
