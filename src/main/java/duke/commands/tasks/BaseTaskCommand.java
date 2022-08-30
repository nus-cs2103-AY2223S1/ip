package duke.commands.tasks;

import duke.commands.BaseCommand;
import duke.tasklist.TaskList;

/**
 * Represents a Base Task.
 */
public abstract class BaseTaskCommand implements BaseCommand {
    protected TaskList taskList;

    /**
     * Returns the input string formatted with borders.
     *
     * @param text
     *            Text to be formatted
     * @return Formatted text
     */
    public String formatOutputString(String text) {
        String borderMessage = "______________________";
        return String.format(
                "\n%s\n%s\n%s\n",
                borderMessage,
                text,
                borderMessage);
    }

    /**
     * @param taskList
     *            Task List to be set
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}
