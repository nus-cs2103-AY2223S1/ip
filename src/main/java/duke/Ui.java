package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private static String NAME = "DoiMoiBot: ";

    /**
     * Constructor to create instance of duke.Ui.
     */
    public Ui() {
         this.sc = new Scanner(System.in);
    }

    /**
     * Method prints out duke.Duke's default greeting.
     */
    public String greet() {
        return "Hello! I'm doimoibot\n" + "What can I do you for?";
    }

    /**
     * Method to encapsulate the printing of seperator lines.
     */
    public void printLine() {
        System.out.println("------------------------------------------------------------------------------------");
    }

    /**
     * Method prints duke.Duke's default farewell.
     * @return returns string representation of notification.
     */
    public String farewell() {
        System.out.println(NAME + "Goodbye! See you soon!");
        return NAME + "Goodbye! See you soon!";
    }

    /**
     * Method reads the user input.
     *
     * @return String representation of user input.
     */
    public String readInput() {
        return this.sc.nextLine();
    }

    /**
     * Method prints out notification that specified task has been added to task list.
     *
     * @param task duke.task.Task to be added to task list.
     * @return returns string representation of notification.
     */
    public String notifyAdded(Task task) {
        System.out.println("Successfully added Task!\n" + task);
        return "Successfully added Task!\n" + task;
    }

    /**
     * Method prints notification that specified task has been marked done or undone according to boolean parameter.
     *
     * @param task The task that has been marked.
     * @param isDone The status of the task has been marked as.
     * @return returns string representation of notification.
     */
    public String notifyMarked(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Marked as done!\n" + task);
            return "Marked as done!\n" + task;
        } else {
            System.out.println("Marked as not done!\n" + task);
            return "Marked as not done!\n" + task;
        }
    }

    /**
     * Method prints notification that specified task has been deleted from task list.
     *
     * @param task duke.task.Task to be deleted from task list.
     * @return returns string representation of notification
     */
    public String notifyDeleted(Task task) {
        System.out.println("Deleted task!\n" + task);
        return "Deleted task!\n" + task;
    }

    /**
     * Prints notification whether tasks have been found.
     */
    public void notifyFound() {
        System.out.println("I've found these tasks:");
    }

    /**
     * Prints out string representation of task.
     *
     * @param task Task whose string representation is to be printed out.
     */
    public void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Method prints out the information currently stored in the task list.
     * @param tasks duke.TaskList object whose information will be printed.
     * @return String representation of list of tasks to print.
     */
    public String printList(TaskList tasks) {
        String toPrint = "";
        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(i + ": " + tasks.get(i - 1));
            toPrint = toPrint + i + ": " + tasks.get(i - 1) + "\n";
        }
        return toPrint;
    }
}
