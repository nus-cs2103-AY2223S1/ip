package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private final int position;

    public DeleteCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        Task deletedTask = taskList.getTask(position - 1);
        taskList.remove(position - 1, storage);
        String commandMessage = "Noted! This task has been successfully removed!";
        ui.displayCommandMessage(commandMessage, deletedTask, taskList.getSize());
        ui.printBorder();
    }
}
