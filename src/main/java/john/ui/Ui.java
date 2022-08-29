package john.ui;

import john.data.TaskList;

/**
 * The text UI of the program.
 */
public class Ui {
    /**
     * Constructor for Ui.
     */
    public Ui() {}

    /**
     * Returns a string representing the goodbye message.
     * @return A string representing the goodbye message.
     */
    public String showGoodbye() {
        return "Goodbye!";
    }

    /**
     * Returns a string representing the added task.
     * @param task The task added.
     * @param taskList The task list the task was added to.
     * @return A string representing the added task.
     */
    public String showAddedTask(String task, TaskList taskList) {
        return String.format("I've added this task!%n%s.%n%s", task, showTotalTasks(taskList));
    }

    /**
     * Returns a string representing the deleted task.
     * @param task The task deleted.
     * @param taskList The task list the task was deleted from.
     * @return A string representing the deleted task.
     */
    public String showDeletedTask(String task, TaskList taskList) {
        return String.format("I've deleted this task!%n%s.%n%s", task, showTotalTasks(taskList));
    }

    /**
     * Returns a string representing the unmarked task.
     * @param task The task unmarked.
     * @return A string representing the unmarked task.
     */
    public String showUnmarkedTask(String task) {
        return String.format("I've unmarked this task!%n%s.", task);
    }

    /**
     * Returns a string representing the marked task.
     * @param task The task marked.
     * @return A string representing the marked task.
     */
    public String showMarkedTask(String task) {
        return String.format("I've marked this task as complete!%n%s.", task);
    }

    /**
     * Returns a string representing the total number of tasks in the task list.
     * @param taskList The task list containing the tasks.
     * @return A string representing the total number of tasks in the task list.
     */
    private String showTotalTasks(TaskList taskList) {
        return String.format("You have %d %s now.", taskList.getNumberOfTasks(), (taskList.getNumberOfTasks() == 1
                ? "task"
                : "tasks"));
    }

    /**
     * Returns a string if an invalid task number is used.
     * @param taskList The task list containing the tasks.
     * @return A string representing the invalid task number.
     */
    public String showInvalidTaskNumber(TaskList taskList) {
        return String.format("This is an invalid task number.%nYou have %d tasks in your list.",
                taskList.getNumberOfTasks());
    }

    /**
     * Returns a string representing the tasks to display.
     * @param tasks The tasks to display.
     * @return A string representing the tasks to display.
     */
    public String showTasks(String ... tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.length; ++i) {
            if (tasks[i] != null) {
                sb.append(String.format("%d. %s%n", i + 1, tasks[i]));
            }
        }
        return sb.toString();
    }

    /**
     * Returns a string showing that there are no tasks for the find or list query.
     * @param taskList The task list containing the tasks.
     * @param params The parameters of the query.
     * @return A string showing that there are no tasks for the query.
     */
    public String showNoTasks(TaskList taskList, String params) {
        return taskList.getNumberOfTasks() == 0
                ? "There are no tasks in your list."
                : "There are no tasks found for '" + params + "'.";
    }

    /**
     * Returns a string representing an unknown command.
     * @param command The unknown command entered.
     * @return Returns a string representing an unknown command.
     */
    public String showIncorrectCommand(String command) {
        return String.format("I cannot understand '%s'. Try another command!", command);
    }

    /**
     * Returns a string representing a known command with an invalid format.
     * @param command The command entered.
     * @param format The correct format of the command.
     * @return A string representing a known command with an invalid format.
     */
    public String showIncorrectCommandWithFormat(String command, String format) {
        return String.format(
                "This is an invalid %s format.%nThe correct format is '%s'.", command.toUpperCase(), format);
    }
}
