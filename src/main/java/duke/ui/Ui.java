package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.common.Messages;
import duke.data.TaskList;
import duke.tasks.Task;

/**
 * Displays messages to the user to provide information of the tasks or of errors encountered.
 */
public class Ui {
    private final Scanner in;

    /**
     * Constructor for a UI.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads and returns the user input per line.
     * @return the user input.
     */
    public String readInput() {
        return in.nextLine();
    }

    /**
     * Displays a message to the user.
     * @param message The message to be shown to the user.
     */
    public void show(String message) {
        System.out.println(message);
    }

    /**
     * Displays the greeting message to the user.
     */
    public void showGreeting() {
        show(Messages.MESSAGE_GREETING);
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        show(Messages.MESSAGE_GOODBYE);
        in.close();
    }

    /**
     * Displays the task added to the user.
     */
    public void showTaskAdded(Task task) {
        show(Messages.MESSAGE_TASK_ADDED + task);
    }

    /**
     * Displays the task removed to the user.
     */
    public void showTaskRemoved(Task task) {
        show(Messages.MESSAGE_TASK_REMOVED + task);
    }

    /**
     * Displays the task marked as done to the user.
     */
    public void showTaskDone(Task task) {
        show(Messages.MESSAGE_TASK_DONE + task);
    }

    /**
     * Displays the task marked as not done to the user.
     */
    public void showTaskNotDone(Task task) {
        show(Messages.MESSAGE_TASK_NOT_DONE + task);
    }

    /**
     * Displays the number of tasks to the user.
     */
    public void showNumberOfTasks(int count) {
        show(String.format(Messages.MESSAGE_NUMBER_OF_TASKS, count));
    }

    /**
     * Displays all tasks on the list to the user.
     */
    public void showAllTasks(TaskList taskList) {
        int size = taskList.numTasks();
        if (size != 0) {
            show(Messages.MESSAGE_TASK_LIST);
            for (int i = 0; i < size; i++) {
                int taskNum = i + 1;
                Task task = taskList.getTask(i);
                show(taskNum + "." + task);
            }
        } else {
            show(Messages.MESSAGE_EMPTY_TASK_LIST);
        }
    }

    /**
     * Displays all matching tasks on the list to the user.
     * @param matchingTasks The list of matching tasks.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        int size = matchingTasks.size();
        if (size != 0) {
            show(Messages.MESSAGE_MATCHING_TASKS);
            int taskNum = 1;
            for (Task task : matchingTasks) {
                show(taskNum + "." + task);
                taskNum++;
            }
        } else {
            show(Messages.MESSAGE_NO_MATCHING_TASKS);
        }
    }
}
