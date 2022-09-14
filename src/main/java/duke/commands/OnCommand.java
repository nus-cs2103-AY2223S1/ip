package duke.commands;

import java.util.List;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Represents the command to find matching tasks that occur on a specific date.
 */
public class OnCommand extends Command {
    public static final String COMMAND_WORD = "on";
    private String date;

    /**
     * Constructor for an OnCommand.
     * @param date The date of tasks to find.
     */
    public OnCommand(String date) {
        super();
        this.date = date;
    }

    /**
     * Finds matching tasks occurring on a specific date.
     * @param taskList List of tasks.
     * @param ui Shows the tasks that occur on the given date.
     * @param storage Saves the modified list of tasks.
     * @return A message indicating the matching tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> matchingTasks = taskList.getTasksOnDate(date);
        return ui.showMatchingTasks(matchingTasks);
    }
}
