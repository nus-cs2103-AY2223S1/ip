package duke.commands.tasks;

import duke.commands.BaseCommand;
import duke.tasklist.TaskList;

/**
 * BaseTaskCommand class
 */
public abstract class BaseTaskCommand implements BaseCommand {
    protected TaskList taskList;
    private final String borderMessage = "______________________";

    /**
     * The formatOutputString function formats the output string by adding a divider
     * and
     * returning it.
     *
     * @param text
     *            Pass the text to be formatted
     * @return A string that includes the text
     */
    public String formatOutputString(String text) {
        return String.format(
                "\n%s\n%s\n%s\n",
                borderMessage,
                text,
                borderMessage);
    }

    /**
     * @param taskList
     *            the taskList to set
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}
