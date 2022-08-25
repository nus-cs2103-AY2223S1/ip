package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private final int position;

    public UnmarkCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        Task unmarkedTask = taskList.getTask(position - 1);
        taskList.unmark(position - 1, storage);
        String commandMessage = "Congratulations! This task has been successfully unmarked!";
        ui.displayCommandMessage(commandMessage, unmarkedTask, null);
        ui.printBorder();
    }
}
