package duke.commands;

import duke.storage.Storage;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.tasks.Deadline;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        super();
        this.description = description;
        this.by = by;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.description, this.by);
        taskList.addTask(deadline);
        ui.showTaskAdded(deadline);
        ui.showNumberOfTasks(taskList.numTasks());
    }
}
