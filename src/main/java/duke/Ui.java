package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Class that handles interactions with the user
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    private boolean isExit = false;

    /**
     * Takes in user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a horizontal line
     */
    public static void separationLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a welcome message
     */
    public void welcome() {
        separationLine();
        System.out.println("Hello, I'm Duke\n"
                + "what can I do for you?");
        separationLine();
    }

    /**
     * Prints a goodbye message
     */
    public void bye() {
        separationLine();
        System.out.println("Sorry to see you go, goodbye :(");
        separationLine();
    }

    /**
     * Prints a message for when task is to be
     * added
     */
    public static void addTaskLog(Task task) {
        separationLine();
        System.out.println("Ok, new task for you: \n"
                + " " + task);
        System.out.print("You now have " + TaskList.length() + " tasks. \n");
        separationLine();
    }

    /**
     * Prints a message for when task is to be
     * removed
     */
    public static void removeTaskLog(Task task) {
        separationLine();
        System.out.println("Ok, I've removed this task for you: \n"
                + " " + task);
        System.out.print("You now have " + (TaskList.length() - 1) + " tasks. \n");
        separationLine();
    }

    /**
     * Prints a message for when task is to be
     * marked as done/not done
     */
    public static void markLog(Task task, boolean isDone) {
        separationLine();
        if (isDone) {
            System.out.println("This task is done, goodjob! :)");
        } else {
            System.out.println("This task hasn't been done yet? I've updated it for you");
        }
        System.out.println(task);
        separationLine();
    }

    /**
     * Changes isExit boolean to be true
     */
    public void exit() {
        this.isExit = true;
    }

    /**
     * Returns value of isExit boolean
     */
    public boolean isExit() {
        return this.isExit;
    }
}
