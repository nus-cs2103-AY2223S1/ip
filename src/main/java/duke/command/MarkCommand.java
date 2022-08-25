package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private final int position;

    public MarkCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        Task markedTask = taskList.getTask(position - 1);
        taskList.mark(position - 1, storage);
        String commandMessage = "Congratulations! This task has been marked as done!";
        ui.displayCommandMessage(commandMessage, markedTask, null);
        ui.printBorder();
    }
}
