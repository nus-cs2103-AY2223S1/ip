package KKBot.ui;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import KKBot.tasks.Task;
import KKBot.tasklist.TaskList;

/**
 * Ui class that includes all text-based visual elements
 * for KKBot.
 * Only used for welcome message.
 *
 * @author AkkFiros
 */

public class Ui {

    /**
     * Empty constructor for Ui class
     */
    public Ui() {}

    private static String DIVIDER = "____________________________________________________________\n";

    private static String LOGO = " __   __   __   __  _____\n"
            + "|  | /  / |  | /  /|  __  \\\n"
            + "|  |/  /  |  |/  / | |__|  |\n"
            + "|     /   |     /  |      /\n"
            + "|     \\   |     \\  |  __  \\\n"
            + "|  |\\  \\  |  |\\  \\ | |__|  |\n"
            + "|__| \\__\\ |__| \\__\\|______/\n";

    private static String WELCOMEMESSAGE = "Hello! I'm KKBot! \n"
            + "What can I do for you?\n";

    /**
     * Method to print welcome message whenever KKBot is initialised.
     * @return welcome message
     */
    public String showWelcome() {
        return DIVIDER + LOGO + WELCOMEMESSAGE + DIVIDER;
    }

    /**
     * Method to print closer message whenever program is closed.
     * @return closer message
     */
    public String showCloser() {
        return DIVIDER + "KKBot signing off. Goodbye!\n" + DIVIDER;
    }

    /**
     * Method to print message whenever a task is added to TaskList.
     * @param task the task to be added to TaskList
     * @return the message that the task has been added
     */
    public String showTaskAddition(Task task) {
        return DIVIDER + "Got it! Task added:\n " + task + "\n" + DIVIDER;
    }

    /**
     * Method to print message whenever a task is deleted from TaskList.
     * @param task the task to be removed from TaskList
     * @return the message that the task has been removed
     */
    public String showTaskDeletion(Task task) {
        return DIVIDER + "Gotcha! Task deleted:\n " + task + "\n" + DIVIDER;
    }

    /**
     * Method to print message whenever a task is marked as 'Done'.
     * @param task the task that is completed
     * @return the message that the task has been marked as done
     */
    public String showTaskDone(Task task) {
        return DIVIDER + "Nicely done!\n\nTask marked as done:\n " + task + "\n" + DIVIDER;
    }

    /**
     * Method to print message whenever a task is marked as 'Not Done'.
     * @param task the task that is not completed
     * @return the message that the task has been marked as not done
     */
    public String showTaskUndone(Task task) {
        return DIVIDER + "OI! Are you trynna cheat?! ):<\n\nTask marked as not done:\n " + task + "\n" + DIVIDER;
    }

    /**
     * Method to print the number of tasks.
     * @param count the number of tasks
     * @return the message informing the user of the number of tasks
     */
    public String showNumberOfTasks(int count) {
        if (count < 0) {
            return DIVIDER + "Don't break the 4th wall! You can't have a negative number of tasks!" + DIVIDER;
        }
        if (count == 0) {
            return DIVIDER + "Woohoo! You don't have any tasks!" + DIVIDER;
        }
        return DIVIDER + String.format("\n\nYou have %d task(s)!", count) + DIVIDER;
    }

    /**
     * Returns a string representation of the list of all the tasks stored in KKBot.
     * @param tasks the list of tasks stored in KKBot
     * @return the string representation of all tasks on the list
     */
    public String showAllTasks(TaskList tasks) {
        int numOfTasks = tasks.getNumberOfTasks();
        if (numOfTasks == 0) {
            return DIVIDER + "Woohoo! You don't have any tasks!" + DIVIDER;
        }

        StringBuilder fullList = new StringBuilder("Here's your list of tasks:\n");
        for (int i = 0; i < numOfTasks; i++) {
            int index = i + 1;
            Task task = tasks.getTask(i);
            fullList.append(String.format("%d.%s\n", index, task));
        }
        return fullList.toString();
    }
}
