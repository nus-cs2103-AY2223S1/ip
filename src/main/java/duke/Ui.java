package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;


/**
 * A component of the chatBot Duke that prints out its interactions.
 */
public class Ui {
    private Scanner scanner;
    private boolean isActive;

    /**
     * Creates a Ui object with an active scanner to read user inputs.
     */
    public Ui() {
        scanner = new Scanner(System.in);
        isActive = true;
    }

    public String retrieveUserInput() {
        return scanner.nextLine();
    }

    public boolean isScannerActive() {
        return isActive;
    }

    /**
     * Prints the intro message of the chatBot Duke.
     */
    public void printIntro() {
        printDivider();
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        printDivider();
    }

    /**
     * Prints the outro message of the chatBot Duke.
     */
    public void printOutro() {
        isActive = false;
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    /**
     * Prints the error message of any caught DukeException.
     * @param msg The message associated to the caught DukeException.
     */
    public void printError(String msg) {
        System.out.println(msg);
        printDivider();
    }

    /**
     * Prints the message that a task has been mark as completed.
     * Additionally, prints the task it is referring to.
     * @param task The task that is marked as completed.
     */
    public void printMarkTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:" + "\n  " + task);
        printDivider();
    }

    /**
     * Prints the message that a task has been mark as uncompleted.
     * Additionally, prints the task it is referring to.
     * @param task The task that is marked as uncompleted.
     */
    public void printMarkTaskUndone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:" + "\n  " + task);
        printDivider();
    }

    /**
     * Prints the message that a task has been deleted.
     * Additionally, prints the task it is referring to and the resultant list size.
     * @param list The tasklist that the task has been removed from.
     * @param task The task that is deleted.
     */
    public void printDeletedTask(TaskList list, Task task) {
        System.out.println("Noted. I've removed this task:" + "\n  " + task
                + "\nNow you have " + list.getListSize() + " tasks in the list.");
        printDivider();
    }
    /**
     * Prints the message that a task has been added.
     * Additionally, prints the task it is referring to and the resultant list size.
     * @param list The taskList that the task has been added to.
     * @param task The task that is added.
     */
    public void printAddedTask(TaskList list, Task task) {
        System.out.println("Got it. I've added this task:\n  " + task
                + "\nNow you have " + list.getListSize() + " tasks in the list.");
        printDivider();
    }

    /**
     * Prints out all the tasks in the given taskList.
     * @param list The taskList containing the tasks.
     */
    public void printActiveTasks(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.getListSize(); i++) {
            System.out.println((i + 1) + "." + list.retrieveTask(i));
        }
        printDivider();
    }

    /**
     * Prints out all the due tasks in the given arraylist.
     * @param list The arraylist containing the queried tasks.
     */
    public void printDueTasks(ArrayList<Task> list) {
        System.out.println("Here are the tasks due at this date:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        printDivider();
    }


    /**
     * Prints the list of tasks found in the query.
     * @param list The given list of found tasks.
     */
    public void printFoundTasks(ArrayList<Task> list) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        printDivider();
    }
    private void printDivider() {
        System.out.println("_________________________________________________________________");
    }

}
