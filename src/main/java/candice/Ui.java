package candice;

import candice.task.Task;
import candice.task.TaskList;

/**
 * Prints out visual response to update the user on what is going on.
 */
public class Ui {
    /**
     * Returns a message for when the Candice program starts running.
     *
     * @return The starting message.
     */
    public static String getMessageForStartingUp() {
        return "Sup bro! My name is Candice.\nI'm here to help you track your tasks!";
    }

    /**
     * Returns a message for when the Candice program shuts down.
     *
     * @return The exit message.
     */
    public static String getMessageForShuttingDown() {
        return "Bye bro. See you soon.";
    }

    /**
     * Returns a message for when a task is added to the task list.
     *
     * @param newTask The task that was added to the task list.
     * @param taskList The task list that the task was added to.
     * @return The message reflecting that a task has been added.
     */
    public static String getMessageForAddTask(Task newTask, TaskList taskList) {
        return "I gotchu fam. Your task has been added:\n"
                + newTask.getStatus() + "\n"
                + "You have " + taskList.getLength() + " task(s). Stop procrastinating bro.";

    }

    /**
     * Returns a message for when a task is deleted from the task list.
     *
     * @param removedTask The task that was removed from the task list.
     * @param taskList The task list that the task was removed from.
     * @return The message reflecting that a task has been deleted.
     */
    public static String getMessageForDeleteTask(Task removedTask, TaskList taskList) {
        return "I hope you're not deleting this just to avoid work:\n"
                + removedTask.getStatus() + "\n"
                + "You have " + taskList.getLength() + " task(s). Stop procrastinating bro.";
    }

    /**
     * Returns a message for when a task has been marked as finished.
     *
     * @param selectedTask The task that was marked as finished.
     * @return The message reflecting that a task has been marked as finished.
     */
    public static String getMessageForMarkTask(Task selectedTask) {
        return "Nice one lah, finally doing your work.\n" + selectedTask.getStatus();
    }

    /**
     * Returns a message for when a task has been marked as unfinished.
     *
     * @param selectedTask The task that was marked as unfinished.
     * @return The message reflecting that a task has been marked as unfinished.
     */
    public static String getMessageForUnmarkTask(Task selectedTask) {
        return "What happened bro.\n" + selectedTask.getStatus();
    }

    /**
     * Prints the current task list.
     *
     * @param taskList The task list to be printed.
     * @return The current task list.
     */
    public static String getMessageForList(TaskList taskList) {
        return "Here are your tasks. You better start now:\n" + taskList;
    }

    /**
     * Prints out the tasks that have a name containing the task keyword.
     *
     * @param taskList The task list that is being searched for the keyword.
     * @param taskKeyword The keyword that the name of the task should contain.
     * @return The tasks in the current task list that have task names containing the keyword.
     */
    public static String getMessageForFind(TaskList taskList, String taskKeyword) {
        return "No problem fam. Here are the tasks that match the keyword:\n"
                + taskList.findTasks(taskKeyword);
    }
}
