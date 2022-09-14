package john.ui;

import john.commands.ByeCommand;
import john.commands.DeadlineCommand;
import john.commands.DeleteCommand;
import john.commands.EventCommand;
import john.commands.FindCommand;
import john.commands.ListCommand;
import john.commands.MarkCommand;
import john.commands.TodoCommand;
import john.commands.UnmarkCommand;
import john.data.TaskList;

/**
 * The UI of the program, which feeds responses into John.
 */
public class Ui {
    /**
     * Constructor for Ui.
     */
    public Ui() {}

    public String showGreeting() {
        return "Hello, I'm John. What can I do for you today?";
    }

    /**
     * Returns a string representing the goodbye message.
     *
     * @return A string representing the goodbye message.
     */
    public String showGoodbye() {
        return "Goodbye!";
    }

    /**
     * Returns a string representing the added task.
     *
     * @param task The task added.
     * @param taskList The task list the task was added to.
     * @return A string representing the added task.
     */
    public String showAddedTask(String task, TaskList taskList) {
        return String.format("I've added this task!%n%s%n%s", task, showTotalTasks(taskList));
    }

    /**
     * Returns a string representing the deleted task.
     *
     * @param task The task deleted.
     * @param taskList The task list the task was deleted from.
     * @return A string representing the deleted task.
     */
    public String showDeletedTask(String task, TaskList taskList) {
        return String.format("I've deleted this task!%n%s%n%s", task, showTotalTasks(taskList));
    }

    /**
     * Returns a string representing the unmarked task.
     *
     * @param task The task unmarked.
     * @return A string representing the unmarked task.
     */
    public String showUnmarkedTask(String task) {
        return String.format("I've unmarked this task!%n%s", task);
    }

    /**
     * Returns a string representing the marked task.
     *
     * @param task The task marked.
     * @return A string representing the marked task.
     */
    public String showMarkedTask(String task) {
        return String.format("I've marked this task as complete!%n%s", task);
    }

    /**
     * Returns a string representing the total number of tasks in the task list.
     *
     * @param taskList The task list containing the tasks.
     * @return A string representing the total number of tasks in the task list.
     */
    private String showTotalTasks(TaskList taskList) {
        return String.format("You have %d %s in your list.",
                taskList.getNumberOfTasks(), (taskList.getNumberOfTasks() == 1
                ? "task"
                : "tasks"));
    }

    /**
     * Returns a string if an invalid task number is used.
     *
     * @param taskList The task list containing the tasks.
     * @return A string representing the invalid task number.
     */
    public String showInvalidTaskNumber(TaskList taskList) {
        return String.format("This is an invalid task number.%n%s", showTotalTasks(taskList));
    }

    /**
     * Returns a string representing the tasks to display.
     *
     * @param taskList The task list to search in.
     * @param params The search parameters, if any.
     * @param tasks The tasks to display.
     * @return A string representing the tasks to display.
     */
    public String showTasks(TaskList taskList, String params, String ... tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.length; ++i) {
            if (tasks[i] != null && sb.length() == 0) {
                sb.append(String.format("%d. %s", i + 1, tasks[i]));
            } else if (tasks[i] != null) {
                sb.append(String.format("%n%d. %s", i + 1, tasks[i]));
            }
        }
        if (sb.length() == 0) {
            return showNoTasks(taskList, params);
        }
        return sb.toString();
    }

    private String showNoTasks(TaskList taskList, String params) {
        return taskList.getNumberOfTasks() == 0
                ? "There are no tasks in your list."
                : "There are no tasks found for '" + params + "'.";
    }

    /**
     * Returns a string representing an unknown command.
     *
     * @param command The unknown command entered.
     * @return Returns a string representing an unknown command.
     */
    public String showIncorrectCommand(String command) {
        return String.format("I cannot understand '%s'. Try another command!", command);
    }

    /**
     * Returns a string representing a known command with an invalid format.
     *
     * @param command The command entered.
     * @param format The correct format of the command.
     * @return A string representing a known command with an invalid format.
     */
    public String showIncorrectCommandWithFormat(String command, String format) {
        return String.format(
                "This is an invalid %s format.%nThe correct format is '%s'.", command.toUpperCase(), format);
    }

    /**
     * Returns a string showing all the available commands in the chatbot.
     *
     * @return A string showing all the available commands in the chatbot.
     */
    public String showHelp() {
        return "Here's the list of commands that I know! The commands are not case-sensitive.\n\n"
                + "Creating Tasks\n"
                + String.format("1. %s: Add a todo task\n", TodoCommand.FORMAT)
                + String.format("2. %s: Add a deadline task\n", DeadlineCommand.FORMAT)
                + String.format("3. %s: Add a event task\n\n", EventCommand.FORMAT)
                + "Editing Tasks\n"
                + String.format("1. %s: Mark task as done\n", MarkCommand.FORMAT)
                + String.format("2. %s: Unmark task\n\n", UnmarkCommand.FORMAT)
                + "Deleting Tasks\n"
                + String.format("1. %s: Delete specified task\n\n", DeleteCommand.FORMAT)
                + "Showing Tasks\n"
                + String.format("1. %s: Lists tasks (on a specific date)\n", ListCommand.FORMAT)
                + String.format("2. %s: Find tasks by keyword\n\n", FindCommand.FORMAT)
                + "Exiting\n"
                + String.format("1. %s: Exiting the application", ByeCommand.FORMAT);
    }
}
