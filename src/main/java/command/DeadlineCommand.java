package command;

import duke.Constants;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Deadline;

import java.time.LocalDateTime;

/**
 * Creates a new deadline task
 */
public class DeadlineCommand extends Command {
    private String desc;
    private LocalDateTime by;

    /**
     * Constructor that creates a new deadline command with the taskname and date given
     *
     * @param desc
     * @param by
     */
    public DeadlineCommand(String desc, LocalDateTime by) {
        super();
        this.desc = desc;
        this.by = by;
    }

    /**
     * Creates a new deadline task and adds it into the task list,
     * followed by saving it into the filepath given and
     * finally prints the necessary lines for the user interface.
     *
     * @param taskList which contains the current task list
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     * @return string that will be printed in the UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.desc, this.by);
        boolean isDuplicated = taskList.isTasksDuplicated(deadline);
        if (isDuplicated) {
            return Constants.ERROR_IF_DUPLICATED;
        } else {
            taskList.addTask(deadline);
            storage.writeFile(taskList.tasksToString());
            return ui.printAddTask(deadline, taskList.getSize());
        }
    }
}
