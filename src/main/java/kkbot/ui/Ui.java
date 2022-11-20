package kkbot.ui;

import java.util.List;
import java.util.Scanner;

import kkbot.tasks.Task;
import kkbot.tasklist.TaskList;

/**
 * Ui class that includes all text-based visual elements
 * for KKBot.
 * Only used for welcome message.
 *
 * @author AkkFiros
 */

public class Ui {
    private final Scanner sc;

    /**
     * Constructor for Ui class
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static String DIVIDER = "_______________________________________________\n";

    private static String LOGO = " __   __   __   __  ____\n"
            + "|   | /  /  |  | /  /  |  __  \\\n"
            + "|   |/  /   |  |/  /   | |__|  |\n"
            + "|      /     |     /    |        /\n"
            + "|      \\     |     \\    |  __  \\\n"
            + "|   |\\  \\   |  |\\  \\   | |__|  |\n"
            + "|_ | \\_ \\ |_ | \\_ \\ |____ /\n";

    private static String WELCOME_MESSAGE = "Hello! I'm KKBot! \n"
            + "What can I do for you?\n";

    /**
     * Method to print welcome message whenever KKBot is initialised.
     * @return welcome message
     */
    public String showWelcome() {
        System.out.println(DIVIDER + LOGO
                + WELCOME_MESSAGE + DIVIDER);
        return DIVIDER + LOGO + WELCOME_MESSAGE + DIVIDER;
    }

    /**
     * Method to print closer message whenever program is closed.
     * @return closer message
     */
    public String showCloser() {
        System.out.println(DIVIDER
                + "KKBot signing off. Goodbye!\n" + DIVIDER);
        return DIVIDER + "KKBot signing off. Goodbye!\n" + DIVIDER;
    }

    /**
     * Method to print out a message.
     * @param message message to be printed out
     * @return the printed out message
     */
    public String show(String message) {
        System.out.println(message);
        return message;
    }

    /**
     * Method to read a user input
     * @return the user input as a string
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Method to print message whenever a task is added to TaskList.
     * @param task the task to be added to TaskList
     * @return the message that the task has been added
     */
    public String showTaskAddition(Task task) {
        System.out.println(DIVIDER
                + "\nGot it! Task added:\n " + task + "\n" + DIVIDER);
        return DIVIDER
                + "\nGot it! Task added:\n " + task + "\n" + DIVIDER;
    }

    /**
     * Method to print message whenever a task is deleted from TaskList.
     * @param task the task to be removed from TaskList
     * @return the message that the task has been removed
     */
    public String showTaskDeletion(Task task) {
        System.out.println(DIVIDER
                + "\nGotcha! Task deleted:\n " + task + "\n" + DIVIDER);
        return DIVIDER
                + "\nGotcha! Task deleted:\n " + task + "\n" + DIVIDER;
    }

    /**
     * Method to print message whenever a task is marked as 'Done'.
     * @param task the task that is completed
     * @return the message that the task has been marked as done
     */
    public String showTaskDone(Task task) {
        System.out.println(DIVIDER
                + "\nNicely done!\n\nTask marked as done:\n " + task + "\n" + DIVIDER);
        return DIVIDER
                + "\nNicely done!\n\nTask marked as done:\n " + task + "\n" + DIVIDER;
    }

    /**
     * Method to print message whenever a task is marked as 'Not Done'.
     * @param task the task that is not completed
     * @return the message that the task has been marked as not done
     */
    public String showTaskUndone(Task task) {
        System.out.println(DIVIDER
                + "\nOI! Are you trynna cheat?! ):<\n\nTask marked as not done:\n "
                + task + "\n" + DIVIDER);
        return DIVIDER
                + "\nOI! Are you trynna cheat?! ):<\n\nTask marked as not done:\n "
                + task + "\n" + DIVIDER;
    }

    /**
     * Method to print the number of tasks.
     * @param count the number of tasks
     * @return the message informing the user of the number of tasks
     */
    public String showNumberOfTasks(int count) {
        assert count >= 0 : "Silly you, you can't have a negative number of tasks!";
        System.out.println(DIVIDER + String.format("\nYou have %d task(s)!", count) + "\n" + DIVIDER);
        return DIVIDER + String.format("\nYou have %d task(s)!", count) + "\n" +  DIVIDER;
    }

    /**
     * Returns a string representation of the list of all the tasks stored in KKBot.
     * @param tasks the list of tasks stored in KKBot
     * @return the string representation of all tasks on the list
     */
    public String showAllTasks(TaskList tasks) {
        int numOfTasks = tasks.getNumberOfTasks();
        if (numOfTasks == 0) {
            System.out.println(DIVIDER
                    + "\nWoohoo! You don't have any tasks!" + "\n"
                    + DIVIDER);
            return DIVIDER
                    + "\nWoohoo! You don't have any tasks!" + "\n"
                    + DIVIDER;
        }

        StringBuilder fullList = new StringBuilder(DIVIDER
                                                   + "\nHere's your list of tasks:\n");
        for (int i = 0; i < numOfTasks; i++) {
            int index = i + 1;
            Task task = tasks.getTask(i);
            fullList.append(String.format("%d.%s\n", index, task));
        }
        fullList.append(DIVIDER);
        System.out.print(fullList.toString());
        return fullList.toString();
    }

    /**
     * Method returning a message containing all tasks in the list
     * of matches (from FindCommand).
     * @param matches the list of all matches
     * @return a message of all the matching tasks
     */
    public String showMatches(List<Task> matches) {
        if (matches.size() == 0) {
            System.out.println(DIVIDER + "\nNo matches found!\n" + DIVIDER);
            return DIVIDER + "\nNo matches found!\n" + DIVIDER;
        }

        StringBuilder sb = new StringBuilder(DIVIDER + "\nThe following tasks match your search!\n\nThey are:\n");
        int serialNumber = 1;
        for (Task task : matches) {
            sb.append(String.format("%d.%s\n", serialNumber, task));
            serialNumber++;
        }
        sb.append(DIVIDER);
        System.out.println(sb.toString());
        return sb.toString();
    }
}
