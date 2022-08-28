package duke;

import java.util.Scanner;

/**
 * Class which handles the interaction with the user.
 *
 */
public class Ui {

    /**
     * Prints the opening message.
     *
     */
    public void printStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help?");
    }

    /**
     * Prints all the tasks from the TaskList.
     *
     * @param taskList
     */
    public void printTasks(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("%d. %s", i+1, taskList.getTask(i).toString()));
        }
    }

    /**
     * Prints message when a task is marked as done.
     *
     * @param task
     */
    public void printMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("%s", task.toString()));
    }

    /**
     * Prints message when a task is marked as undone.
     *
     * @param task
     */
    public void printUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(String.format("%s", task.toString()));
    }

    /**
     * Prints a message after user exits program.
     *
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message after a new task is added.
     *
     * @param task
     * @param taskList
     */
    public void printTaskAdded(Task task, TaskList taskList) {
        System.out.println("added: " + task.toString());
        System.out.println(String.format("Now you have %d tasks in the list", taskList.size()));
    }

    /**
     * Prints a message after a task is deleted.
     *
     * @param task
     * @param taskList
     */
    public void printDelete(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list", taskList.size()));
    }

    /**
     * Prints the String representation of a DukeException.
     *
     * @param e
     */
    public void printDukeException(DukeException e) {
        System.out.println(e.toString());
    }

    /**
     * Returns user input.
     *
     * @param sc
     * @return user input
     */
    public String getCommand(Scanner sc) {
        return sc.nextLine();
    }
}
