package duke.ui;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import duke.common.Messages;
import duke.data.TaskList;
import duke.tasks.Task;

/**
 * For displaying of messages to the user to provide information of the tasks or of errors encountered.
 */
public class Ui {
    /**
     * Returns the greeting message to be shown to the user.
     * @return The greeting message.
     */
    public String showGreeting() {
        return Messages.MESSAGE_GREETING;
    }

    /**
     * Returns the goodbye message to be shown to the user.
     * @return The goodbye message.
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
     * Returns the message that the Task has been added to be shown to the user.
     * @param task The Task that has been added.
     * @return The message that the Task has been added.
     */
    public String showTaskAdded(Task task) {
        return Messages.MESSAGE_TASK_ADDED + task;
    }

    /**
     * Returns the message that the Task has been removed to be shown to the user.
     * @param task The Task that has been removed.
     * @return The message that the Task has been removed.
     */
    public String showTaskRemoved(Task task) {
        return Messages.MESSAGE_TASK_REMOVED + task;
    }

    /**
     * Returns the message that the Task has been marked as done to be shown to the user.
     * @param task The Task that has been marked as done.
     * @return The message that the Task has been marked as done.
     */
    public String showTaskDone(Task task) {
        return Messages.MESSAGE_TASK_DONE + task;
    }

    /**
     * Returns the message that the Task has been marked as done to be shown to the user.
     * @param task The Task that has been marked as done.
     * @return The message that the Task has been marked as done.
     */
    public String showTaskNotDone(Task task) {
        return Messages.MESSAGE_TASK_NOT_DONE + task;
    }

    /**
     * Returns a message indicating the number of tasks to be shown to the user.
     * @param count The number of tasks.
     * @return A message indicating the number of tasks.
     */
    public String showNumberOfTasks(int count) {
        return String.format(Messages.MESSAGE_NUMBER_OF_TASKS, count);
    }

    /**
     * Returns a message indicating all tasks on the list to be shown to the user.
     * @param taskList The list of tasks.
     * @return A message indicating all the tasks on the list.
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
     * Returns a message indicating all matching tasks on the list to be shown to the user.
     * @param matchingTasks The list of matching tasks.
     * @return A message indicating all matching tasks on the list.
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
