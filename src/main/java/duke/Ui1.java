package duke;

import java.util.ArrayList;

/**
 * Ui class that helps to print message to the user.
 */
public class Ui1 {

    /**
     * Method that prints to screen a line for ease of reading.
     */
    public String line() {
        return "----------------------";
    }

    public String formatOutput(String output) {
        return line() + "\n" + output + "\n" + line();
    }

    /**
     * Method that prints the farewell message.
     */
    public String printFarewell() {
        return formatOutput("Bye, hope to see you again!");

    }

    /**
     * Method that prints the exception encountered.
     *
     * @param e The exception to be printed.
     */
    public String printException(Exception e) {
        return formatOutput(e.toString());
    }

    /**
     * Method that prints a custom error message.
     *
     * @param s Error message to be printed.
     */
    public String printErrorMessage(String s) {
        return formatOutput(s);

    }

    /**
     * Prints to the user the task added and count in updated TaskList.
     *
     * @param a Task to be added.
     * @param tList TaskList to be added to.
     */
    public String printTaskAdded(Task a, TaskList tList) {
        return formatOutput("added: " + a.toString() + "\n"
                + String.format("Now you have %d tasks in the list", tList.getCount()));

    }

    /**
     * Prints all the tasks in the TaskList.
     *
     * @param tList TaskList to be printed.
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
     * Prints all the tasks matching keyword.
     * @param t The ArrayList containing all matching tasks.
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
     * Prints the task to be marked undone.
     *
     * @param a Task to be marked undone
     */
    public String printMarkTestUndone(Task a) {
        String output = "Ok! I've marked this task as undone" + "\n" + a.toString();
        return formatOutput(output);
    }

    /**
     * Prints the task to be deleted.
     *
     * @param a Task to be deleted
     * @param tList TaskList to be deleted from.
     */
    public String printDelete(Task a, TaskList tList) {
        String output = "Noted! I've removed this task" + "\n" + a.toString() + "\n"
                + "Now you have " + tList.getCount() + " tasks!";
        return formatOutput(output);
    }

    /**
     * Prints the task to be marked done
     * @param a The task being marked.
     */
    public String printMarkTaskDone(Task a) {
        String output = "Ok! I've marked this task as done" + "\n" + a.toString();
        return formatOutput(output);
    }

    /**
     * Prints the greeting message.
     */
    public String printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String output = "Hello! I'm Duke" + "\n" + "What can i do for you?";
        return "Hello from\n" + logo + "\n" + formatOutput(output);

    }
}

