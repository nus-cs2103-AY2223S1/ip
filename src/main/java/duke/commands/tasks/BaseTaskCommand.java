package duke.commands.tasks;

import duke.commands.BaseCommand;
import duke.tasklist.TaskList;

public abstract class BaseTaskCommand implements BaseCommand {
    protected TaskList taskList;

    public String formatOutputString(String text) {
        return String.format(
                "\n ------------------------- \n %s \n ------------------------- \n",
                text);
    }

    /**
     * @param taskList
     *            the taskList to set
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}
