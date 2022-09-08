package commands;

import duke.Storage;
import duke.Ui;
import tasks.Deadlines;
import tasks.Task;
import tasks.TaskList;

/**
 * DeadlineCommand creates and adds a new deadline to the task list.
 */
public class DeadlineCommand extends Command {
    String descript;
    String doBy;

    /**
     * Constructor for DeadlineCommand
     *
     * @param descript Description of deadline task.
     * @param doBy End date and time of deadline task.
     */
    public DeadlineCommand(String descript, String doBy) {
        this.descript = descript;
        this.doBy = doBy;
    }

    @Override
    /**
     * Executes DeadlineCommand by creating and adding new Deadlines object into task list.
     *
     * @param taskList List of tasks to add deadline into.
     */
    public String execute(TaskList taskList, Ui ui, Storage s) {
        Task toAdd = new Deadlines(this.descript, this.doBy);
        taskList.addTask(toAdd);
        return ui.printAddStatement(toAdd.toString(), taskList.getSize());
    }
}
