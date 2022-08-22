package commands;

import storage.Storage;
import data.TaskList;
import ui.Ui;
import tasks.Deadline;

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
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Deadline deadline = new Deadline(description, by);
        taskList.addToList(deadline);
        storage.save(taskList);
        ui.printAddTask(deadline, taskList.getSize());
    }
}
