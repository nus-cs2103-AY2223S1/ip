package duke.command;

import duke.*;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.deleteTask(index);
        ui.printWithIndent("Noted. I've removed this task:");
        ui.printWithIndent("  " + task);
        ui.printTaskCount(taskList.taskCount());
        storage.saveFile(taskList);
    }
}
