package duke;

import java.util.ArrayList;

/**
 * Ui class that helps to print message to the user.
 */
public class Ui {

    /**
     * Method that returns a string of lines for ease of reading.
     *
     * @return String of a line
     */
    public String line() {
        return "----------------------";
    }

    /**
     * Method that returns the string in the correct format.
     *
     * @param output The string to be formatted.
     * @return The string after formatting.
     */
    public String formatOutput(String output) {
        return line() + "\n" + output + "\n" + line();
    }

    /**
     * Method that returns the farewell message.
     *
     * @return The farewell message.
     */
    public String printFarewell() {
        return formatOutput("Bye, hope to see you again!");

    }


    /**
     * Method that returns the exception message encountered.
     * @param e The exception.
     * @return String of exception message.
     */
    public String printException(Exception e) {
        return formatOutput(e.toString());
    }

    /**
     * Method that returns a custom error message.
     *
     * @param s Error message to be printed.
     * @return String of error message.
     */
    public String printErrorMessage(String s) {
        return formatOutput(s);

    }

    /**
     * Returns to the user the task added and count in updated TaskList in a String.
     *
     * @param a Task to be added.
     * @param tList TaskList to be added to.
     * @return String of tasks added.
     */
    public String printTaskAdded(Task a, TaskList tList) {
        return formatOutput("added: " + a.toString() + "\n"
                + String.format("Now you have %d tasks in the list", tList.getCount()));

    }

    /**
     * String of all the tasks in the TaskList.
     *
     * @param tList TaskList to be printed.
     * @return String of all the tasks.
     */
    public String printList(TaskList tList) {
        String output = "Here are your tasks :";
        for (int i = 0; i < tList.getCount(); i++) {
            String display = String.format("%d.%s", i + 1, tList.getTask(i).toString());
            output += "\n" + display;
        }
        return formatOutput(output);
    }

    /**
     * Return all the tasks matching keyword.
     *
     * @param t The ArrayList containing all matching tasks.
     * @return The tasks found in a String.
     */
    public String printFind(ArrayList<Task> t) {
        if (t.size() == 0) {
            return formatOutput("Opps! No matching tasks.");
        } else {
            String output = "Here are the matching tasks in your list:";
            for (int i = 0; i < t.size(); i++) {
                String display = String.format("%d.%s", i + 1, t.get(i).toString());
                output += "\n" + display;
            }
            return formatOutput(output);
        }
    }

    /**
     * Method that returns the String of the task to be marked undone.
     *
     * @param a Task to be marked undone.
     * @return String of task being marked undone.
     */
    public String printMarkTestUndone(Task a) {
        String output = "Ok! I've marked this task as undone" + "\n" + a.toString();
        return formatOutput(output);
    }

    /**
     * Method that returns the String of the task to be deleted.
     *
     * @param a Task to be deleted
     * @param tList TaskList to be deleted from.
     * @return String of task deleted and count of tasks left.
     */
    public String printDelete(Task a, TaskList tList) {
        String output = "Noted! I've removed this task" + "\n" + a.toString() + "\n"
                + "Now you have " + tList.getCount() + " tasks!";
        return formatOutput(output);
    }

    /**
     * Method that returns the task to be marked done.
     *
     * @param a The task being marked.
     * @return String of task being marked done and count of tasks left.
     */
    public String printMarkTaskDone(Task a) {
        String output = "Ok! I've marked this task as done" + "\n" + a.toString();
        return formatOutput(output);
    }

    /**
     * Method that returns the String of the greeting message.
     *
     * @return String of greeting message.
     */
    public static String printGreetings() {

        String output = "----------------------" + "\n"
                + "Hello! I'm Duke" + "\n" + "What can i do for you?" + "\n" + "----------------------";
        return output;

    }
}
