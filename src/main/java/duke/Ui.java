package duke;

import java.util.ArrayList;

/**
 * Ui class that helps to print message to the user.
 */
public class Ui {

    /**
     * Method that prints to screen a line for ease of reading.
     */
    public void line() {
        System.out.println("----------------------");
    }

    /**
     * Method that prints the farewell message.
     */
    public void printFarewell() {
        line();
        System.out.println("Bye, hope to see you again!");
        line();
    }

    /**
     * Method that prints the exception encountered.
     *
     * @param e The exception to be printed.
     */
    public void printException(Exception e) {
        line();
        System.out.println(e.toString());
        line();
    }

    /**
     * Method that prints a custom error message.
     *
     * @param s Error message to be printed.
     */
    public void printErrorMessage(String s) {
        line();
        System.out.println(s);
        line();

    }

    /**
     * Prints to the user the task added and count in updated TaskList.
     *
     * @param a Task to be added.
     * @param tList TaskList to be added to.
     */
    public void printTaskAdded(Task a, TaskList tList) {
        line();
        System.out.println("added: " + a.toString());
        System.out.println(String.format("Now you have %d tasks in the list", tList.getCount()));
        line();

    }

    /**
     * Prints all the tasks in the TaskList.
     *
     * @param tList TaskList to be printed.
     */
    public void printList(TaskList tList) {
        line();
        for (int i = 0; i < tList.getCount(); i++) {
            String display = String.format("%d.%s", i + 1, tList.getTask(i).toString());
            System.out.println(display);
        }
        line();
    }

    /**
     * Prints all the tasks matching keyword.
     * @param t The ArrayList containing all matching tasks.
     */
    public void printFind(ArrayList<Task> t) {
        if (t.size() == 0) {
            line();
            System.out.println("Opps! No matching tasks");
            line();
        } else {
            line();
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < t.size(); i++) {
                String display = String.format("%d.%s", i + 1, t.get(i).toString());
                System.out.println(display);
            }
            line();
        }
    }

    /**
     * Prints the task to be marked undone.
     *
     * @param a Task to be marked undone
     */
    public void printMarkTestUndone(Task a) {
        line();
        System.out.println("Ok! I've marked this task as undone");
        System.out.println(a.toString());
        line();
    }

    /**
     * Prints the task to be deleted.
     *
     * @param a Task to be deleted
     * @param tList TaskList to be deleted from.
     */
    public void printDelete(Task a, TaskList tList) {
        line();
        System.out.println("Noted! I've removed this task");
        System.out.println(a.toString());
        System.out.println("Now you have " + tList.getCount() + " tasks!");
        line();
    }

    /**
     * Prints the task to be marked done
     * @param a The task being marked.
     */
    public void printMarkTaskDone(Task a) {
        line();
        System.out.println("Ok! I've marked this task as done");
        System.out.println(a.toString());
        line();
    }

    /**
     * Prints the greeting message.
     */
    public void printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
        line();

    }
}
