package utils;

import java.util.List;

import entities.Task;

/**
 * Handles printing messages and errors to the user.
 */
public class Ui {

    /**
     * Prints a welcome message to the user.
     */
    public static void welcomeUser() {
        String s = "Hello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + "\t(Please enter any datetime inputs in \n"
                + "\t\"yyyy-mm-dd hhhh\" format)";
        sendMessage(s);
    }

    /**
     * Prints a goodbye message to the user.
     */
    public static void sayGoodbye() {
        sendMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message within the exception thrown.
     * @param e The exception thrown from the method.
     */
    public static void displayErrorMessage(Exception e) {
        sendMessage(e.getMessage());
    }

    /**
     * Utility function for retrieving and printing the tasks in the user's task list.
     */
    public static void printTasks(List<Task> taskList) {
        if (taskList.size() == 0) {
            sendMessage("No items stored");
        } else {
            String s = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                s = s + "\t" + (i + 1) + "." + t + "\n";
            }
            sendMessage(s.trim());
        }
    }

    /**
     * Utility function for Duke to print responses to the user.
     * @param s The string to be formatted and indented within the enclosing border.
     */
    public static void sendMessage(String s) {
        System.out.println("\t_________________________________________________");
        System.out.println("\t" + s);
        System.out.println("\t_________________________________________________");
        System.out.println();
    }

    public static void printErrorWithoutFormatting(Exception e) {
        System.out.print(e.getMessage());
    }
}
