package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Deadline;

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
