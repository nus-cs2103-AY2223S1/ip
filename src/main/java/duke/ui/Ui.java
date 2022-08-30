package duke.ui;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import duke.common.Messages;
import duke.data.TaskList;
import duke.tasks.Task;

/**
 * Displays messages to the user to provide information of the tasks or of errors encountered.
 */
public class Ui {
    /**
     * Displays the greeting message to the user.
     */
    public String showGreeting() {
        return Messages.MESSAGE_GREETING;
    }

    /**
     * Displays the goodbye message to the user.
     */
    public String showGoodbye() {
        new Timer().schedule(new TimerTask() {
            public void run() {
                System.exit(0);
            }
        }, 1000);
        return Messages.MESSAGE_GOODBYE;
    }

    /**
     * Displays the task added to the user.
     */
    public String showTaskAdded(Task task) {
        return Messages.MESSAGE_TASK_ADDED + task;
    }

    /**
     * Displays the task removed to the user.
     */
    public String showTaskRemoved(Task task) {
        return Messages.MESSAGE_TASK_REMOVED + task;
    }

    /**
     * Displays the task marked as done to the user.
     */
    public String showTaskDone(Task task) {
        return Messages.MESSAGE_TASK_DONE + task;
    }

    /**
     * Displays the task marked as not done to the user.
     */
    public String showTaskNotDone(Task task) {
        return Messages.MESSAGE_TASK_NOT_DONE + task;
    }

    /**
     * Displays the number of tasks to the user.
     */
    public String showNumberOfTasks(int count) {
        return String.format(Messages.MESSAGE_NUMBER_OF_TASKS, count);
    }

    /**
     * Displays all tasks on the list to the user.
     */
    public String showAllTasks(TaskList taskList) {
        int size = taskList.numTasks();
        if (size != 0) {
            StringBuilder sb = new StringBuilder(Messages.MESSAGE_TASK_LIST);
            for (int i = 0; i < size; i++) {
                int taskNum = i + 1;
                Task task = taskList.getTask(i);
                sb.append(taskNum + "." + task + "\n");
            }
            return sb.toString();
        } else {
            return Messages.MESSAGE_EMPTY_TASK_LIST;
        }
    }

    /**
     * Displays all matching tasks on the list to the user.
     * @param matchingTasks The list of matching tasks.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        int size = matchingTasks.size();
        if (size != 0) {
            StringBuilder sb = new StringBuilder(Messages.MESSAGE_MATCHING_TASKS);
            int taskNum = 1;
            for (Task task : matchingTasks) {
                sb.append(taskNum + "." + task + "\n");
                taskNum++;
            }
            return sb.toString();
        } else {
            return Messages.MESSAGE_NO_MATCHING_TASKS;
        }
    }
}
