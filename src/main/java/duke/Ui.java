package duke;

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
        System.out.println("----------------------");
        System.out.println(e.toString());
        System.out.println("----------------------");
    }

    /**
     * Method that prints a custom error message.
     *
     * @param s Error message to be printed.
     */
    public void printErrorMessage(String s) {
        System.out.println("----------------------");
        System.out.println(s);
        System.out.println("----------------------");

    }

    /**
     * Prints to the user the task added and count in updated TaskList.
     *
     * @param a Task to be added.
     * @param tList TaskList to be added to.
     */
    public void printTaskAdded(Task a, TaskList tList) {
        System.out.println("----------------------");
        System.out.println("added: " + a.toString());
        System.out.println(String.format("Now you have %d tasks in the list", tList.getCount()));
        System.out.println("----------------------");

    }

    /**
     * Prints all the tasks in the TaskList.
     *
     * @param tList TaskList to be printed.
     */
    public void printList(TaskList tList) {
        System.out.println("----------------------");
        for (int i = 0; i < tList.getCount(); i++) {
            String display = String.format("%d.%s", i + 1, tList.getTask(i).toString());
            System.out.println(display);
        }
        System.out.println("----------------------");
    }

    /**
     * Prints the task to be marked undone.
     *
     * @param a Task to be marked undone
     */
    public void printMarkTestUndone(Task a) {
        System.out.println("----------------------");
        System.out.println("Ok! I've marked this task as undone");
        System.out.println(a.toString());
        System.out.println("----------------------");
    }

    /**
     * Prints the task to be deleted.
     *
     * @param a Task to be deleted
     * @param tList TaskList to be deleted from.
     */
    public void printDelete(Task a, TaskList tList) {
        System.out.println("----------------------");
        System.out.println("Noted! I've removed this task");
        System.out.println(a.toString());
        System.out.println("Now you have " + tList.getCount() + " tasks!");
        System.out.println("----------------------");
    }

    public void printMarkTaskDone(Task a) {
        System.out.println("----------------------");
        System.out.println("Ok! I've marked this task as done");
        System.out.println(a.toString());
        System.out.println("----------------------");
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
