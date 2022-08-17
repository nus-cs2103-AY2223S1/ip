package duke;

import java.util.List;

/**
 * Class that deals with interactions with the user
 * Has static methods and does not need to be initialized (for now)
 */
public class Ui {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    public static void welcomeMessage() {
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \n"
                + "What can I do for you?");
    }

    /**
     * Helper method that prints out a message fed into the UI
     * @param message
     */
    public static String displayMessage(String message) {
        System.out.println(message);
        return message + "\n";
    }

    public static String indentTaskDisplay(Task t) {
        System.out.println("  " + t);
        return (" " + t + "\n");
    }

    /**
     * Given a lists of tasks.
     * Prints it out nicely with the appropriate numbering & indentation
     * @param taskList
     */

    public static String displayOrderedList(List<Task> taskList) {
        String display = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println(i + 1 + ". " + currentTask);
            display += i + 1 + ". " + currentTask + "\n";
        }
        return display;
    }

    /**
     * Returns the number of tasks left
     * @param tasksLeft
     * @return taskLeftDescription, without newline character
     */
    public static String displayTasksLeft(int tasksLeft) {
        System.out.println("Now you have " + tasksLeft + " tasks in the list");
        return "Now you have " + tasksLeft + " tasks in the list";
    }

    public static String displayException(Exception e) {
        System.out.println(e);
        return e.toString();
    }

}
