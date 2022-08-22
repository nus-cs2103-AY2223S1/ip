package duke.commands;

import duke.ui.Ui;
import duke.data.TaskList;
import duke.tasks.Deadline;
import duke.data.exception.DukeException;
import duke.storage.Storage;

public class DeadlineCommand extends Command {

    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Deadline deadline = new Deadline(description, by);
        taskList.addToList(deadline);
        storage.save(taskList);
        ui.printAddTask(deadline, taskList.getSize());
    }
}
