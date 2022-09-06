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
    public static String welcomeUser() {
        String s = "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "(Please enter any datetime inputs in \n"
                + "\"yyyy-mm-dd hhhh\" format)";
        return s;
    }

    /**
     * Prints a goodbye message to the user.
     */
    public static String sayGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the error message within the exception thrown.
     * @param e The exception thrown from the method.
     */
    public static void displayErrorMessage(Exception e) {
        e.getMessage();
    }

    /**
     * Utility function for retrieving and printing the tasks in the user's task list.
     */
    public static String getTasks(List<Task> taskList) {
        if (taskList.size() == 0) {
            return "No items stored";
        } else {
            String s = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                s = s + "\t" + (i + 1) + "." + t + "\n";
            }
            return s.trim();
        }
    }


    public static void printErrorWithoutFormatting(Exception e) {
        System.out.print(e.getMessage());
    }
}
