package duke.ui;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import duke.data.TaskList;
import duke.tasks.Task;

/**
 * For displaying of messages to the user to provide information of the tasks or of errors encountered.
 */
public class Ui {
    /**
     * Returns the greeting message.
     * @return The greeting message.
     */
    public String showGreeting() {
        return "Hello! I'm Pip :)\nWhat can I do for you?";
    }

    /**
     * Returns the goodbye message and closes the program.
     * @return The goodbye message.
     */
    public String showGoodbye() {
        new Timer().schedule(new TimerTask() {
            public void run() {
                System.exit(0);
            }
        }, 1000);
        return "Goodbye and see you again soon!";
    }

    /**
     * Returns the message that the Task has been added.
     * @param task The Task that has been added.
     * @return The message that the Task has been added.
     */
    public String showTaskAdded(Task task) {
        return "Sure! Task added:\n  " + task;
    }

    /**
     * Returns the message that the Task has been removed.
     * @param task The Task that has been removed.
     * @return The message that the Task has been removed.
     */
    public String showTaskRemoved(Task task) {
        return "Alright! Task removed:\n  " + task;
    }

    /**
     * Returns the message that the Task has been marked as done.
     * @param task The Task that has been marked as done.
     * @return The message that the Task has been marked as done.
     */
    public String showTaskDone(Task task) {
        return "Speedy, keep it up!\n\nTask marked as done:\n  " + task;
    }

    /**
     * Returns the message that the Task has been marked as done.
     * @param task The Task that has been marked as done.
     * @return The message that the Task has been marked as done.
     */
    public String showTaskNotDone(Task task) {
        return "Welp, don't fall behind!\n\nTask marked as not done:\n  " + task;
    }

    /**
     * Returns a message indicating the number of tasks.
     * @param count The number of tasks.
     * @return A message indicating the number of tasks.
     */
    public String showNumberOfTasks(int count) {
        assert count >= 0 : "Number of tasks should not be negative";
        return String.format("\n\nYou have %d task(s) in the list.", count);
    }

    /**
     * Returns a message indicating all tasks on the list.
     * @param taskList The list of tasks.
     * @return A message indicating all the tasks on the list.
     */
    public String showAllTasks(TaskList taskList) {
        int size = taskList.getNumberOfTasks();
        if (size == 0) {
            return "Your task list is empty!";
        }

        StringBuilder sb = new StringBuilder("Here's your task list:\n");
        for (int i = 0; i < size; i++) {
            int taskNum = i + 1;
            Task task = taskList.getTask(i);
            sb.append(String.format("%d.%s\n", taskNum, task));
        }
        return sb.toString();
    }

    /**
     * Returns a message that the most recent change to the task list has been undone.
     * @return A message indicating the updated list of tasks.
     */
    public String showUndo(TaskList taskList) {
        return "Easy-peasy! The most recent change to your task list has been undone!\n\n" + showAllTasks(taskList);
    }

    /**
     * Returns a message indicating all matching tasks on the list.
     * @param matchingTasks The list of matching tasks.
     * @return A message indicating all matching tasks on the list.
     */
    public String showMatchingTasks(List<Task> matchingTasks) {
        if (matchingTasks.size() == 0) {
            return "Hmm... there are no matching tasks in your list!";
        }

        StringBuilder sb = new StringBuilder("Aha, found them!\n\nHere are your matching task(s):\n");
        int taskNum = 1;
        for (Task task : matchingTasks) {
            sb.append(String.format("%d.%s\n", taskNum, task));
            taskNum++;
        }
        return sb.toString();
    }
}
